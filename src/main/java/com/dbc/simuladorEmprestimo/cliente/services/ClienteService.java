package com.dbc.simuladorEmprestimo.cliente.services;

import com.dbc.simuladorEmprestimo.cliente.converters.ClienteToDto;
import com.dbc.simuladorEmprestimo.cliente.converters.DtoToCliente;
import com.dbc.simuladorEmprestimo.cliente.converters.DtoToEndereco;
import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EmprestimoDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import com.dbc.simuladorEmprestimo.cliente.enums.TipoRisco;
import com.dbc.simuladorEmprestimo.cliente.repositories.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ClienteService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    public static final Double RENDIMENTO_MENSAL_OITO_MIL = Double.valueOf(8000);
    public static final Double RENDIMENTO_MENSAL_DOIS_MIL = Double.valueOf(2000);

    private ClienteRepository repository;
    private EnderecoService enderecoService;
    private ClienteToDto clienteToDto;
    private DtoToCliente dtoToCliente;
    private DtoToEndereco dtoToEndereco;

    @Autowired
    public ClienteService(ClienteRepository repository, EnderecoService enderecoService, ClienteToDto clienteToDto, DtoToCliente dtoToCliente, DtoToEndereco dtoToEndereco) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.clienteToDto = clienteToDto;
        this.dtoToCliente = dtoToCliente;
        this.dtoToEndereco = dtoToEndereco;
    }

    public Optional<ClienteDto> atualizar(Integer id, ClienteDto clienteEditadoDto) {
        Optional<Cliente> clienteParaSerAlterado = buscarClientePorId(id);

        if (clienteParaSerAlterado.isPresent()) {
            Cliente cliente = atualizarDados(clienteParaSerAlterado.get(), clienteEditadoDto);
            Cliente clienteSalvo = persistir(cliente);
            ClienteDto clienteDto = clienteToDto.convert(clienteSalvo);

            return Optional.ofNullable(clienteDto);
        }

        return Optional.empty();
    }

    private Optional<Cliente> buscarClientePorId(Integer id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    private Cliente atualizarDados(Cliente cliente, ClienteDto clienteEditado) {
        cliente.setNome(clienteEditado.getNome());
        cliente.setSobrenome(clienteEditado.getSobrenome());
        cliente.setRendimentoMensal(clienteEditado.getRendimentoMensal());
        cliente.setRisco(calcularRisco(cliente.getRendimentoMensal()));
        Optional<Endereco> enderecoOptional =  enderecoService.buscarEnderecoPelosDados(clienteEditado.getEnderecoDto());
        if (enderecoOptional.isPresent()) {
            cliente.setEndereco(enderecoOptional.get());
        } else {
            EnderecoDto enderecoSalvo = salvarEndereco(clienteEditado.getEnderecoDto());
            cliente.setEndereco(dtoToEndereco.convert(enderecoSalvo));
        }

        return cliente;
    }

    public ClienteDto adicionarCliente(ClienteDto clienteDto) {
        clienteDto.setRisco(calcularRisco(clienteDto.getRendimentoMensal()));

        Cliente cliente = dtoToCliente.convert(clienteDto);

        Optional<Endereco> enderecoOptional =  enderecoService.buscarEnderecoPelosDados(clienteDto.getEnderecoDto());
        if (enderecoOptional.isPresent()) {
            cliente.setEndereco(enderecoOptional.get());
        } else {
            EnderecoDto enderecoSalvo = salvarEndereco(clienteDto.getEnderecoDto());
            cliente.setEndereco(dtoToEndereco.convert(enderecoSalvo));
        }

        Cliente clienteSalvo = persistir(cliente);
        return clienteToDto.convert(clienteSalvo);
    }

    private EnderecoDto salvarEndereco(EnderecoDto enderecoDto) {
        return enderecoService.salvar(enderecoDto);
    }

    private TipoRisco calcularRisco(Double rendimentoMensal) {
        if (rendimentoMensal <= RENDIMENTO_MENSAL_DOIS_MIL) {
            return TipoRisco.C;
        } else if (rendimentoMensal <= RENDIMENTO_MENSAL_OITO_MIL) {
            return TipoRisco.B;
        } else {
            return TipoRisco.A;
        }
    }

    private Cliente persistir(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<ClienteDto> buscarTodos() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(clienteToDto::convert).collect(toList());
    }

    public void excluirCliente(Integer id) {
        Optional<Cliente> cliente = buscarClientePorId(id);
        if (cliente.isPresent()) {
            repository.delete(cliente.get());
        }
    }

    public Optional<EmprestimoDto> simularEmprestimo(EmprestimoDto emprestimoDto) throws ParseException {
        Optional<Cliente> cliente = buscarClientePorId(emprestimoDto.getIdCliente());
        if (cliente.isPresent()) {
            Double taxaDeJuros = TipoRisco.retornarTaxaDeJuros(cliente.get().getRisco());
            emprestimoDto.setTaxaJuros(taxaDeJuros);
            Double pagamentoMensal = calcularPagamentoMensal(emprestimoDto, taxaDeJuros);

            emprestimoDto.setClienteDto(clienteToDto.convert(cliente.get()));
            emprestimoDto.setValorParcelas(pagamentoMensal);
            emprestimoDto.setMontanteComJuros(pagamentoMensal * emprestimoDto.getNumeroParcelas());

            return Optional.of(emprestimoDto);
        }

        return Optional.empty();
    }

    private double calcularPagamentoMensal(EmprestimoDto emprestimoDto, Double taxaDeJuros) throws ParseException {
        //Fórmula utilizada para o cálculo
        //M = P * ( J / (1 - (1 + J)^ -N))
        Double taxaDeJurosMensal = (taxaDeJuros / 100) / 12;
        Double jurosMaisUm = 1 + taxaDeJurosMensal;
        Double potencia = Double.valueOf(emprestimoDto.getNumeroParcelas());
        Double elevadoPotenciaMeses = Math.pow(jurosMaisUm, -potencia);
        Double menosUm = 1 - elevadoPotenciaMeses;
        Double divindoJurosMensais = taxaDeJurosMensal / menosUm;
        Double valorDasParcelas = emprestimoDto.getMontanteSolicitado() * divindoJurosMensais;

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("0.00");
        df.setRoundingMode(RoundingMode.UP);

        return Double.valueOf(df.format(valorDasParcelas).replace(",", "."));
    }
}

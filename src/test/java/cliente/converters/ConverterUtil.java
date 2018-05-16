package cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import com.dbc.simuladorEmprestimo.cliente.enums.TipoRisco;

public class ConverterUtil {

    private Cliente cliente = new Cliente();
    private ClienteDto clienteDto = new ClienteDto();
    private Endereco endereco = new Endereco();
    private Endereco enderecoComApartamento = new Endereco();
    private EnderecoDto enderecoDto = new EnderecoDto();

    public Cliente getCliente() {
        cliente.setNome("Teste1");
        cliente.setSobrenome("Teste1");
        cliente.setEndereco(getEndereco());
        cliente.setRendimentoMensal(Double.valueOf(1000));
        cliente.setId(1);
        cliente.setRisco(TipoRisco.C);

        return cliente;
    }

    public Endereco getEndereco() {
        endereco.setId(1);
        endereco.setPais("Brasil");
        endereco.setBairro("Bairro");
        endereco.setCep("12345678");
        endereco.setNumero("123");
        endereco.setLogradouro("logradouro");
        endereco.setEstado("RS");
        endereco.setCidade("Cidade");

        return endereco;
    }

    public Endereco getEnderecoComApartamento() {
        enderecoComApartamento.setId(2);
        enderecoComApartamento.setPais("Brasil");
        enderecoComApartamento.setBairro("Bairro");
        enderecoComApartamento.setCep("12345678");
        enderecoComApartamento.setNumero("1234");
        enderecoComApartamento.setLogradouro("logradouro");
        enderecoComApartamento.setEstado("RS");
        enderecoComApartamento.setCidade("Cidade");
        enderecoComApartamento.setApartamento("1");

        return enderecoComApartamento;
    }

    public ClienteDto getClienteDto() {
        clienteDto.setId(1);
        clienteDto.setNome("Teste1");
        clienteDto.setSobrenome("Teste1");
        clienteDto.setRendimentoMensal(Double.valueOf(1000));
        clienteDto.setRisco(TipoRisco.C);
        clienteDto.setEnderecoDto(getEnderecoDto());

        return clienteDto;
    }

    public EnderecoDto getEnderecoDto() {
        enderecoDto.setPais("Brasil");
        enderecoDto.setId(1);
        enderecoDto.setBairro("Bairro");
        enderecoDto.setCep("12345678");
        enderecoDto.setNumero("123");
        enderecoDto.setLogradouro("logradouro");
        enderecoDto.setEstado("RS");
        enderecoDto.setCidade("Cidade");

        return enderecoDto;
    }
}

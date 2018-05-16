package com.dbc.simuladorEmprestimo.cliente.services;

import cliente.converters.ConverterUtil;
import com.dbc.simuladorEmprestimo.cliente.converters.ClienteToDto;
import com.dbc.simuladorEmprestimo.cliente.converters.DtoToCliente;
import com.dbc.simuladorEmprestimo.cliente.converters.DtoToEndereco;
import com.dbc.simuladorEmprestimo.cliente.dtos.EmprestimoDto;
import com.dbc.simuladorEmprestimo.cliente.repositories.ClienteRepository;
import org.junit.Test;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

public class ClienteServiceTest {

    private ClienteService clienteService;
    private ClienteRepository clienteRepository;
    private EnderecoService enderecoService;
    private ClienteToDto clienteToDto;
    private DtoToCliente dtoToCliente;
    private DtoToEndereco dtoToEndereco;
    private ConverterUtil converterUtil;

    public ClienteServiceTest() {
        converterUtil = new ConverterUtil();
        this.clienteRepository = mock(ClienteRepository.class);
        this.enderecoService = mock(EnderecoService.class);
        this.clienteToDto = mock(ClienteToDto.class);
        this.dtoToCliente = mock(DtoToCliente.class);
        this.dtoToEndereco = mock(DtoToEndereco.class);
        this.clienteService = new ClienteService(clienteRepository, enderecoService, clienteToDto, dtoToCliente, dtoToEndereco);
    }

    @Test
    public void simularEmprestimo() throws ParseException {
        given(clienteRepository.findOne(anyInt())).willReturn(converterUtil.getCliente());

        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimoDto.setIdCliente(1);
        emprestimoDto.setNumeroParcelas(360);
        emprestimoDto.setMontanteSolicitado(Double.valueOf(5000));
        Optional<EmprestimoDto> emprestimoDtoAtual = clienteService.simularEmprestimo(emprestimoDto);

        assertEquals(Double.valueOf(43.88), emprestimoDtoAtual.get().getValorParcelas());
    }
}
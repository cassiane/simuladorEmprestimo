package com.dbc.simuladorEmprestimo.cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToCliente implements Converter<ClienteDto, Cliente> {

    private DtoToEndereco dtoToEndereco;

    @Autowired
    public DtoToCliente(DtoToEndereco dtoToEndereco) {
        this.dtoToEndereco = dtoToEndereco;
    }

    @Override
    public Cliente convert(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setSobrenome(clienteDto.getSobrenome());
        cliente.setEndereco(dtoToEndereco.convert(clienteDto.getEnderecoDto()));
        cliente.setRendimentoMensal(clienteDto.getRendimentoMensal());
        cliente.setRisco(clienteDto.getRisco());

        return cliente;
    }
}

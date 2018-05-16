package com.dbc.simuladorEmprestimo.cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClienteToDto implements Converter<Cliente, ClienteDto> {

    private EnderecoToDto enderecoToDto;

    @Autowired
    public ClienteToDto(EnderecoToDto enderecoToDto) {
        this.enderecoToDto = enderecoToDto;
    }

    @Override
    public ClienteDto convert(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setEnderecoDto(enderecoToDto.convert(cliente.getEndereco()));
        dto.setNome(cliente.getNome());
        dto.setRendimentoMensal(cliente.getRendimentoMensal());
        dto.setRisco(cliente.getRisco());
        dto.setSobrenome(cliente.getSobrenome());

        return dto;
    }
}

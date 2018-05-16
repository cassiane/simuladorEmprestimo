package com.dbc.simuladorEmprestimo.cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnderecoToDto implements Converter<Endereco, EnderecoDto> {

//    private ClienteToDto clienteToDto;
//
//    @Autowired
//    public EnderecoToDto(ClienteToDto clienteToDto) {
//        this.clienteToDto = clienteToDto;
//    }

    @Override
    public EnderecoDto convert(Endereco endereco) {
        EnderecoDto dto = new EnderecoDto();
        dto.setId(endereco.getId());
        dto.setPais(endereco.getPais());
        dto.setEstado(endereco.getEstado());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setApartamento(endereco.getApartamento());
        dto.setCep(endereco.getCep());
//        dto.setClientes(
//                endereco.getClientes().stream()
//                        .map(cliente -> clienteToDto.convert(cliente))
//                        .collect(Collectors.toList()));

        return dto;
    }
}

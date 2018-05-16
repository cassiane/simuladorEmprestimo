package com.dbc.simuladorEmprestimo.cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToEndereco implements Converter<EnderecoDto, Endereco> {

//    private DtoToCliente dtoToCliente;
//
//    @Autowired
//    public DtoToEndereco(DtoToCliente dtoToCliente) {
//        this.dtoToCliente = dtoToCliente;
//    }

    @Override
    public Endereco convert(EnderecoDto dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setPais(dto.getPais());
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setApartamento(dto.getApartamento());
        endereco.setCep(dto.getCep());
//        endereco.setClientes(
//                dto.getClientes().stream()
//                        .map(clienteDto -> dtoToCliente.convert(clienteDto))
//                        .collect(Collectors.toList()));

        return endereco;
    }
}

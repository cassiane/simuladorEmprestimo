package com.dbc.simuladorEmprestimo.cliente.services;

import com.dbc.simuladorEmprestimo.cliente.converters.DtoToEndereco;
import com.dbc.simuladorEmprestimo.cliente.converters.EnderecoToDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import com.dbc.simuladorEmprestimo.cliente.repositories.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class EnderecoService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EnderecoService.class);

    private EnderecoRepository repository;
    private EnderecoToDto enderecoToDto;
    private DtoToEndereco dtoToEndereco;

    @Autowired
    public EnderecoService(EnderecoRepository repository, EnderecoToDto enderecoToDto, DtoToEndereco dtoToEndereco) {
        this.repository = repository;
        this.enderecoToDto = enderecoToDto;
        this.dtoToEndereco = dtoToEndereco;
    }

    public EnderecoDto salvar(EnderecoDto enderecoDto) {
        Endereco endereco = dtoToEndereco.convert(enderecoDto);
        return enderecoToDto.convert(salvar(endereco));
    }

    private Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    public Optional<Endereco> buscarEnderecoPelosDados(EnderecoDto enderecoDto) {
        if (isNull(enderecoDto.getApartamento()) || enderecoDto.getApartamento().isEmpty()) {
            return findByCepAndNumero(enderecoDto);
        }

        return findByCepAndNumeroAndApartamento(enderecoDto);
    }

    public Optional<EnderecoDto> buscarEnderecoPeloCep(String cep) {
        Optional<Endereco> endereco = repository.findByCep(cep);
        return Optional.ofNullable(enderecoToDto.convert(endereco.get()));
    }

    private Optional<Endereco> findByCepAndNumero(EnderecoDto enderecoDto) {
        return repository.findByCepAndNumero(enderecoDto.getCep(), enderecoDto.getNumero());
    }

    private Optional<Endereco> findByCepAndNumeroAndApartamento(EnderecoDto enderecoDto) {
        return repository.findByCepAndNumeroAndApartamento(enderecoDto.getCep(), enderecoDto.getNumero(), enderecoDto.getApartamento());
    }
}

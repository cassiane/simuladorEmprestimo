package com.dbc.simuladorEmprestimo.cliente.repositories;

import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository  extends JpaRepository<Endereco, Integer> {

    Optional<Endereco> findByCepAndNumero(String cep, String numero);
    Optional<Endereco> findByCepAndNumeroAndApartamento(String cep, String numero, String apartamento);
    Optional<Endereco> findByCep(String cep);
}

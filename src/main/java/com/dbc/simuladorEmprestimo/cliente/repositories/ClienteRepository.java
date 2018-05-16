package com.dbc.simuladorEmprestimo.cliente.repositories;

import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {
}

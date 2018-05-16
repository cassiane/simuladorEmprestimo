package com.dbc.simuladorEmprestimo.cliente.controllers;

import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/endereco")
@CrossOrigin(origins = "*")
public class EnderecoController {

    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping(value = "/{cep}")
    public ResponseEntity<EnderecoDto> buscarEnderecoPorCep(@PathVariable("cep") String cep) {
        Optional<EnderecoDto> enderecoDto = enderecoService.buscarEnderecoPeloCep(cep);
        return ResponseEntity.ok(enderecoDto.get());
    }
}

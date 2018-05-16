package com.dbc.simuladorEmprestimo.cliente.controllers;


import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EmprestimoDto;
import com.dbc.simuladorEmprestimo.cliente.services.ClienteService;
import com.dbc.simuladorEmprestimo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Response<ClienteDto>> cadastrar(@Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
        Response<ClienteDto> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(clienteService.adicionarCliente(clienteDto));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<ClienteDto>> alterar(@PathVariable("id") Integer id,
                                                        @Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
        Response<ClienteDto> response = new Response<>();
        Optional<ClienteDto> cliente = clienteService.atualizar(id, clienteDto);

        if (!cliente.isPresent()) {
            result.addError(new ObjectError("cliente", "Cliente não encontrado"));
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(cliente.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable("id") Integer id) {
        clienteService.excluirCliente(id);
    }

    @PostMapping(value = "/simular-emprestimo")
    public ResponseEntity<Response<EmprestimoDto>> simularEmprestimo(@Valid @RequestBody EmprestimoDto emprestimoDto,
                                                                     BindingResult result) throws ParseException {
        Response<EmprestimoDto> response = new Response<>();
        Optional<EmprestimoDto> emprestimo = clienteService.simularEmprestimo(emprestimoDto);

        if (!emprestimo.isPresent()) {
            result.addError(new ObjectError("cliente", "Cliente não encontrado"));
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(emprestimo.get());

        return ResponseEntity.ok(response);
    }
}

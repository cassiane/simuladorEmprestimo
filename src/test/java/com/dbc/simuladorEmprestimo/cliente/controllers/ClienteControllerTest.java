package com.dbc.simuladorEmprestimo.cliente.controllers;

import cliente.converters.ConverterUtil;
import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EmprestimoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import com.dbc.simuladorEmprestimo.cliente.repositories.ClienteRepository;
import com.dbc.simuladorEmprestimo.cliente.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

    private static final String URL = "/api/cliente";
    private ConverterUtil converterUtil = new ConverterUtil();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Before
    public void setUp() {
        clienteRepository.save(converterUtil.getCliente());
    }

    @After
    public void tearDown() throws Exception {
        clienteRepository.deleteAll();
    }

    @Test
    public void buscarTodos() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].nome", Matchers.is(converterUtil.getCliente().getNome())));
    }

    @Test
    public void cadastrar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClienteDto clienteDto = converterUtil.getClienteDto();
        clienteDto.setRisco(null);
        clienteDto.setId(null);
        clienteDto.getEnderecoDto().setId(null);

        mvc.perform(MockMvcRequestBuilders.post(URL)
                .content(mapper.writeValueAsString(clienteDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nome", Matchers.is(converterUtil.getClienteDto().getNome())))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    public void alterar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClienteDto clienteDto = converterUtil.getClienteDto();
        clienteDto.setRisco(null);
        clienteDto.setId(null);
        clienteDto.getEnderecoDto().setId(null);
        clienteDto.setNome("Nome Alterado");

        List<Cliente> cliente = clienteRepository.findAll();

        mvc.perform(MockMvcRequestBuilders.put(URL + "/" + cliente.get(0).getId())
                .content(mapper.writeValueAsString(clienteDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nome", Matchers.is("Nome Alterado")))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    public void excluir() throws Exception {
        List<Cliente> cliente = clienteRepository.findAll();

        mvc.perform(MockMvcRequestBuilders.delete(URL + "/" +cliente.get(0).getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        cliente = clienteRepository.findAll();
        assertTrue(cliente.isEmpty());
    }

    @Test
    public void simularEmprestimo() throws Exception {
        List<Cliente> cliente = clienteRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimoDto.setIdCliente(cliente.get(0).getId());
        emprestimoDto.setNumeroParcelas(360);
        emprestimoDto.setMontanteSolicitado(Double.valueOf(5000));
        
        mvc.perform(MockMvcRequestBuilders.post(URL + "/simular-emprestimo")
                .content(mapper.writeValueAsString(emprestimoDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.valorParcelas", Matchers.is(Double.valueOf(43.88))))
                .andExpect(jsonPath("$.errors").isEmpty());
    }
}
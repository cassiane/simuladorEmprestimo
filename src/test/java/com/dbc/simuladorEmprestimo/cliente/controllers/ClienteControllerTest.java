package com.dbc.simuladorEmprestimo.cliente.controllers;

import cliente.converters.ConverterUtil;
import com.dbc.simuladorEmprestimo.cliente.repositories.ClienteRepository;
import com.dbc.simuladorEmprestimo.cliente.services.ClienteService;
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

import static org.hamcrest.Matchers.hasSize;
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
    public void cadastrar() {
    }

    @Test
    public void alterar() {
    }

    @Test
    public void excluir() {
    }

    @Test
    public void simularEmprestimo() {
    }
}
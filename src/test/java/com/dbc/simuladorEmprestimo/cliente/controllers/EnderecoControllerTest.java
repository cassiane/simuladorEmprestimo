package com.dbc.simuladorEmprestimo.cliente.controllers;

import cliente.converters.ConverterUtil;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import com.dbc.simuladorEmprestimo.cliente.repositories.EnderecoRepository;
import com.dbc.simuladorEmprestimo.cliente.services.EnderecoService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoControllerTest {

    private static final String URL = "/api/endereco";
    private ConverterUtil converterUtil = new ConverterUtil();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Before
    public void setUp() {
        enderecoRepository.save(converterUtil.getEndereco());
    }

    @After
    public void tearDown() throws Exception {
        enderecoRepository.deleteAll();
    }

    @Test
    public void buscarEnderecoPorCep() throws Exception {
        List<Endereco> endereco = enderecoRepository.findAll();

        mvc.perform(MockMvcRequestBuilders.get(URL + "/" + endereco.get(0).getCep())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logradouro", Matchers.is(converterUtil.getEndereco().getLogradouro())));
    }
}
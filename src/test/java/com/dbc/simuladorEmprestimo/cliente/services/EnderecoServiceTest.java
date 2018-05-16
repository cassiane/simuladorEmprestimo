package com.dbc.simuladorEmprestimo.cliente.services;

import cliente.converters.ConverterUtil;
import com.dbc.simuladorEmprestimo.cliente.converters.EnderecoToDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import com.dbc.simuladorEmprestimo.cliente.repositories.EnderecoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EnderecoServiceTest {

    private ConverterUtil converterUtil = new ConverterUtil();
    private Endereco enderecoEsperado;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoToDto enderecoToDto;

    @Before
    public void setUp() throws Exception {
        enderecoEsperado = enderecoRepository.save(converterUtil.getEndereco());
    }

    @After
    public void tearDown() throws Exception {
        enderecoRepository.deleteAll();
    }

    @Test
    public void salvar() {
        EnderecoDto enderecoDtoEsperado = converterUtil.getEnderecoDto();
        enderecoDtoEsperado.setId(null);

        enderecoService.salvar(enderecoDtoEsperado);

        List<Endereco> enderecos = enderecoRepository.findAll();
        assertThat(enderecos, hasSize(1));
    }

    @Test
    public void buscarEnderecoPelosDados() {
        Optional<Endereco> endereco = enderecoService.buscarEnderecoPelosDados(converterUtil.getEnderecoDto());
        assertEquals(enderecoEsperado, endereco.get());
    }

    @Test
    public void buscarEnderecoPeloCep() {
        Optional<EnderecoDto> endereco = enderecoService.buscarEnderecoPeloCep(enderecoEsperado.getCep());
        assertEquals(enderecoToDto.convert(enderecoEsperado), endereco.get());
    }
}
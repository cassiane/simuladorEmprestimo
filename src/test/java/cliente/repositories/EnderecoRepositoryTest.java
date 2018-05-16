package cliente.repositories;

import cliente.converters.ConverterUtil;
import com.dbc.simuladorEmprestimo.SimuladorEmprestimoApplication;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorEmprestimoApplication.class)
@ActiveProfiles("test")
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private ConverterUtil converterUtil = new ConverterUtil();

    @Before
    public void setUp() {
        Endereco endereco = converterUtil.getEndereco();
        Endereco enderecoComApartamento = converterUtil.getEnderecoComApartamento();
        enderecoRepository.save(endereco);
        enderecoRepository.save(enderecoComApartamento);
    }

    @After
    public void tearDown(){
        this.enderecoRepository.deleteAll();
    }

    @Test
    public void findByCepAndNumero() {
        Endereco enderecoEsperado = converterUtil.getEndereco();
        Optional<Endereco> enderecoAtual = enderecoRepository.findByCepAndNumero(enderecoEsperado.getCep(), enderecoEsperado.getNumero());
        assertEquals(enderecoEsperado.getCep(), enderecoAtual.get().getCep());
    }

    @Test
    public void findByCepAndNumeroAndApartamento() {
        //given
        Endereco enderecoEsperado = converterUtil.getEnderecoComApartamento();

        //when
        Optional<Endereco> enderecoAtual = enderecoRepository.findByCepAndNumeroAndApartamento(
                enderecoEsperado.getCep(),
                enderecoEsperado.getNumero(),
                enderecoEsperado.getApartamento());

        //then
        assertEquals(enderecoEsperado.getApartamento(), enderecoAtual.get().getApartamento());
    }
}
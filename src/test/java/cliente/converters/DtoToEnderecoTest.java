package cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.converters.DtoToEndereco;
import com.dbc.simuladorEmprestimo.cliente.entities.Endereco;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtoToEnderecoTest {

    private DtoToEndereco dtoToEndereco;
    private ConverterUtil converterUtil;

    public DtoToEnderecoTest() {
        converterUtil = new ConverterUtil();
        this.dtoToEndereco = new DtoToEndereco();
    }

    @Test
    public void deveConverterDtoParaEndereco() {
        //given
        Endereco enderecoEsperado = converterUtil.getEndereco();

        //when
        Endereco enderecoAtual = dtoToEndereco.convert(converterUtil.getEnderecoDto());

        //then
        assertEquals(enderecoEsperado, enderecoAtual);
    }
}
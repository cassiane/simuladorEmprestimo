package cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.converters.EnderecoToDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnderecoToDtoTest {

    private EnderecoToDto enderecoToDto;
    private ConverterUtil converterUtil;

    public EnderecoToDtoTest() {
        converterUtil = new ConverterUtil();
        enderecoToDto = new EnderecoToDto();
   }

    @Test
    public void deveConverterEnderecoParaDto() {
        //given
        EnderecoDto enderecoEsperado = converterUtil.getEnderecoDto();

        //when
        EnderecoDto enderecoAtual =  enderecoToDto.convert(converterUtil.getEndereco());

        //then
        assertEquals(enderecoEsperado, enderecoAtual);
    }
}
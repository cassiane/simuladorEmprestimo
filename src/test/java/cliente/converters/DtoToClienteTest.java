package cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.converters.DtoToCliente;
import com.dbc.simuladorEmprestimo.cliente.converters.DtoToEndereco;
import com.dbc.simuladorEmprestimo.cliente.dtos.EnderecoDto;
import com.dbc.simuladorEmprestimo.cliente.entities.Cliente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class DtoToClienteTest {

    private DtoToEndereco dtoToEndereco;
    private DtoToCliente dtoToCliente;
    private ConverterUtil converterUtil;

    public DtoToClienteTest() {
        converterUtil = new ConverterUtil();
        dtoToEndereco = mock(DtoToEndereco.class);
        dtoToCliente = new DtoToCliente(dtoToEndereco);
    }

    @Test
    public void deveConverterDtoParaCliente() {
        //given
        Cliente clienteEsperado = converterUtil.getCliente();
        given(dtoToEndereco.convert(any(EnderecoDto.class))).willReturn(converterUtil.getEndereco());

        //when
        Cliente clienteAtual = dtoToCliente.convert(converterUtil.getClienteDto());

        //then
        assertEquals(clienteEsperado, clienteAtual);
    }
}
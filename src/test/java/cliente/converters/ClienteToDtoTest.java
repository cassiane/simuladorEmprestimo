package cliente.converters;

import com.dbc.simuladorEmprestimo.cliente.converters.ClienteToDto;
import com.dbc.simuladorEmprestimo.cliente.converters.EnderecoToDto;
import com.dbc.simuladorEmprestimo.cliente.dtos.ClienteDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClienteToDtoTest {

    private ClienteToDto clienteToDto;
    private EnderecoToDto enderecoToDto;
    private ConverterUtil converterUtil;

    public ClienteToDtoTest() {
        this.enderecoToDto = mock(EnderecoToDto.class);
        this.clienteToDto = new ClienteToDto(enderecoToDto);
        this.converterUtil = new ConverterUtil();
        initMocks(this);
    }

    @Test
    public void deveConverterClienteParaDto() {
        //given
        given(enderecoToDto.convert(anyObject())).willReturn(converterUtil.getEnderecoDto());
        //when
        ClienteDto clienteAtual = clienteToDto.convert(converterUtil.getCliente());
        //then
        assertEquals(converterUtil.getClienteDto(), clienteAtual);
    }
}
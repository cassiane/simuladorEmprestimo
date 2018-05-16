package com.dbc.simuladorEmprestimo.cliente.dtos;

import com.dbc.simuladorEmprestimo.emprestimo.enums.TipoRiscoEnum;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class ClienteDto {

    private Integer id;
    private String nome;
    private String sobrenome;
    private Double rendimentoMensal;
    private TipoRiscoEnum risco;
    private EnderecoDto endereco;

    @Null
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(message = "O campo [nome] é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull(message = "O campo [sobrenome] é obrigatório")
    @Size(min = 3, max = 200, message = "Sobrenome deve conter entre 3 e 200 caracteres.")
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @NotNull(message = "O campo [rendimentoMensal] é obrigatório")
    @Digits(integer = 10, fraction = 2, message = "Rendimento mensal deve possuir duas casas decimais e no máximo 10 dígitos.")
    public Double getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(Double rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    @Valid
    @NotNull(message = "O campo [endereco] é obrigatório")
    public EnderecoDto getEnderecoDto() {
        return endereco;
    }

    public void setEnderecoDto(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    @Null
    public TipoRiscoEnum getRisco() {
        return risco;
    }

    public void setRisco(TipoRiscoEnum risco) {
        this.risco = risco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteDto)) return false;
        ClienteDto dto = (ClienteDto) o;
        return Objects.equal(getId(), dto.getId()) &&
                Objects.equal(getNome(), dto.getNome()) &&
                Objects.equal(getSobrenome(), dto.getSobrenome()) &&
                Objects.equal(getRendimentoMensal(), dto.getRendimentoMensal()) &&
                getRisco() == dto.getRisco() &&
                Objects.equal(endereco, dto.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getNome(), getSobrenome(), getRendimentoMensal(), getRisco(), endereco);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("nome", nome)
                .add("sobrenome", sobrenome)
                .add("rendimentoMensal", rendimentoMensal)
                .add("risco", risco)
                .add("endereco", endereco)
                .add("enderecoDto", getEnderecoDto())
                .toString();
    }
}

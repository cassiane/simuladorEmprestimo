package com.dbc.simuladorEmprestimo.cliente.dtos;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

public class EnderecoDto {

    private Integer id;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private String apartamento;
    private String cep;
    private List<ClienteDto> clientes;

    @Null
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @NotNull
    @Size(min = 2, max = 2, message = "Informe a sigla do estado. Exemplo: RS")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @NotNull
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @NotNull
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @NotNull
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @NotNull
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    @NotNull
    @Size(min = 8, max = 8, message = "Informe o cep com oito dígitos sem traço.")
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<ClienteDto> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDto> clientes) {
        this.clientes = clientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnderecoDto)) return false;
        EnderecoDto that = (EnderecoDto) o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getPais(), that.getPais()) &&
                Objects.equal(getEstado(), that.getEstado()) &&
                Objects.equal(getCidade(), that.getCidade()) &&
                Objects.equal(getBairro(), that.getBairro()) &&
                Objects.equal(getLogradouro(), that.getLogradouro()) &&
                Objects.equal(getNumero(), that.getNumero()) &&
                Objects.equal(getApartamento(), that.getApartamento()) &&
                Objects.equal(getCep(), that.getCep()) &&
                Objects.equal(getClientes(), that.getClientes());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getPais(), getEstado(), getCidade(), getBairro(), getLogradouro(), getNumero(), getApartamento(), getCep(), getClientes());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("pais", pais)
                .add("estado", estado)
                .add("cidade", cidade)
                .add("bairro", bairro)
                .add("logradouro", logradouro)
                .add("numero", numero)
                .add("apartamento", apartamento)
                .add("cep", cep)
                .add("clientes", clientes)
                .toString();
    }
}

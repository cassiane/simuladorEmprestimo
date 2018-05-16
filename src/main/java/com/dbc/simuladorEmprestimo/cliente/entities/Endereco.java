package com.dbc.simuladorEmprestimo.cliente.entities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "endereco")
public class Endereco {

    private Integer id;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private String apartamento;
    private String cep;
    private List<Cliente> clientes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "pais", nullable = false)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Column(name = "estado", nullable = false)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "cidade", nullable = false)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Column(name = "bairro", nullable = false)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "logradouro", nullable = false)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Column(name = "numero", nullable = false)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "apartamento")
    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    @Column(name = "cep", nullable = false)
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @OneToMany(mappedBy = "endereco")
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equal(getPais(), endereco.getPais()) &&
                Objects.equal(getEstado(), endereco.getEstado()) &&
                Objects.equal(getCidade(), endereco.getCidade()) &&
                Objects.equal(getBairro(), endereco.getBairro()) &&
                Objects.equal(getLogradouro(), endereco.getLogradouro()) &&
                Objects.equal(getNumero(), endereco.getNumero()) &&
                Objects.equal(getApartamento(), endereco.getApartamento()) &&
                Objects.equal(getCep(), endereco.getCep());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPais(), getEstado(), getCidade(), getBairro(), getLogradouro(), getNumero(), getApartamento(), getCep());
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pais", pais)
                .add("estado", estado)
                .add("cidade", cidade)
                .add("bairro", bairro)
                .add("logradouro", logradouro)
                .add("numero", numero)
                .add("apartamento", apartamento)
                .add("cep", cep)
                .toString();
    }
}

package com.dbc.simuladorEmprestimo.cliente.entities;

import com.dbc.simuladorEmprestimo.cliente.enums.TipoRisco;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente {

    private Integer id;
    private String nome;
    private String sobrenome;
    private Double rendimentoMensal;
    private TipoRisco risco;
    private Endereco endereco;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nome", nullable = false)
    @NotNull
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "sobrenome", nullable = false)
    @NotNull
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Column(name = "redimento_mensal", nullable = false)
    @NotNull
    public Double getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(Double rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    @Column(name = "risco", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    public TipoRisco getRisco() {
        return risco;
    }

    public void setRisco(TipoRisco risco) {
        this.risco = risco;
    }

    @ManyToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", foreignKey=@ForeignKey(name="FK_ENDERECO_ID"))
    @NotNull
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equal(getNome(), cliente.getNome()) &&
                Objects.equal(getSobrenome(), cliente.getSobrenome()) &&
                Objects.equal(getRendimentoMensal(), cliente.getRendimentoMensal()) &&
                getRisco() == cliente.getRisco() &&
                Objects.equal(getEndereco(), cliente.getEndereco());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome(), getSobrenome(), getRendimentoMensal(), getRisco(), getEndereco());
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nome", nome)
                .add("sobrenome", sobrenome)
                .add("rendimentoMensal", rendimentoMensal)
                .add("risco", risco)
                .add("endereco", endereco)
                .toString();
    }
}

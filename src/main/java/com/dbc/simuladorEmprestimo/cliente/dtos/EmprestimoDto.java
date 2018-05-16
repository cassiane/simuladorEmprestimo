package com.dbc.simuladorEmprestimo.cliente.dtos;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class EmprestimoDto {

    private Integer idCliente;
    private ClienteDto clienteDto;
    private Integer numeroParcelas;
    private Double taxaJuros;
    private Double montanteSolicitado;
    private Double montanteComJuros;
    private Double valorParcelas;

    @NotNull(message = "Campo [idCliente] é obrigatório")
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Null
    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }

    @NotNull(message = "Campo [número de parcelas] é obrigatório")
    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    @Null
    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    @NotNull(message = "Campo [montante solicitado] é obrigatório")
    public Double getMontanteSolicitado() {
        return montanteSolicitado;
    }

    public void setMontanteSolicitado(Double montanteSolicitado) {
        this.montanteSolicitado = montanteSolicitado;
    }

    @Null
    public Double getMontanteComJuros() {
        return montanteComJuros;
    }

    public void setMontanteComJuros(Double montanteComJuros) {
        this.montanteComJuros = montanteComJuros;
    }

    @Null
    public Double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(Double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmprestimoDto)) return false;
        EmprestimoDto that = (EmprestimoDto) o;
        return Objects.equal(getIdCliente(), that.getIdCliente()) &&
                Objects.equal(getClienteDto(), that.getClienteDto()) &&
                Objects.equal(getNumeroParcelas(), that.getNumeroParcelas()) &&
                Objects.equal(getTaxaJuros(), that.getTaxaJuros()) &&
                Objects.equal(getMontanteSolicitado(), that.getMontanteSolicitado()) &&
                Objects.equal(getMontanteComJuros(), that.getMontanteComJuros()) &&
                Objects.equal(getValorParcelas(), that.getValorParcelas());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdCliente(), getClienteDto(), getNumeroParcelas(), getTaxaJuros(), getMontanteSolicitado(), getMontanteComJuros(), getValorParcelas());
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("idCliente", idCliente)
                .add("clienteDto", clienteDto)
                .add("numeroParcelas", numeroParcelas)
                .add("taxaJuros", taxaJuros)
                .add("montanteSolicitado", montanteSolicitado)
                .add("montanteComJuros", montanteComJuros)
                .add("valorParcelas", valorParcelas)
                .toString();
    }
}

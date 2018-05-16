package com.dbc.simuladorEmprestimo.emprestimo.enums;

public enum TipoRiscoEnum {
    A("A", Double.valueOf(1.9)),
    B("B", Double.valueOf(5)),
    C("C", Double.valueOf(10));

    private String risco;
    private Double taxaDeJuros;

    TipoRiscoEnum(String risco, Double taxaDeJuros) {
        this.risco = risco;
        this.taxaDeJuros = taxaDeJuros;
    }

    public static Double retornarTaxaDeJuros(TipoRiscoEnum risco) {
        for (TipoRiscoEnum tipoRiscoEnum : TipoRiscoEnum.values()) {
            if (tipoRiscoEnum.equals(risco)) {
                return tipoRiscoEnum.getTaxaDeJuros();
            }
        }
        return null;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public Double getTaxaDeJuros() {
        return taxaDeJuros;
    }

    public void setTaxaDeJuros(Double taxaDeJuros) {
        this.taxaDeJuros = taxaDeJuros;
    }
}

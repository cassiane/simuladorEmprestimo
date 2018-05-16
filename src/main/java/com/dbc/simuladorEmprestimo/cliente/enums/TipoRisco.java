package com.dbc.simuladorEmprestimo.cliente.enums;

public enum TipoRisco {
    A("A", Double.valueOf(1.9)),
    B("B", Double.valueOf(5)),
    C("C", Double.valueOf(10));

    private String risco;
    private Double taxaDeJuros;

    TipoRisco(String risco, Double taxaDeJuros) {
        this.risco = risco;
        this.taxaDeJuros = taxaDeJuros;
    }

    public static Double retornarTaxaDeJuros(TipoRisco risco) {
        for (TipoRisco TipoRisco : TipoRisco.values()) {
            if (TipoRisco.equals(risco)) {
                return TipoRisco.getTaxaDeJuros();
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

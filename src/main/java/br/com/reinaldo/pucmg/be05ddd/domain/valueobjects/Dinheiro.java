package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

import java.util.Currency;

public class Dinheiro {
    private Double valor;
    private Currency moeda;

    public Dinheiro(Double valor, Currency moeda) {
        this.valor = valor;
        this.moeda = moeda;
    }

    public void adicionar(Dinheiro valorAdicional) {
        if (moeda != valorAdicional.moeda) {
            throw new RuntimeException("Moeda incompatível!");
        }
        this.valor += valorAdicional.valor;
    }

    public Double getValor() {
        return valor;
    }

    public Currency getMoeda() {
        return moeda;
    }
}

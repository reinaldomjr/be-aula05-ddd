package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

import java.util.UUID;

public class Vendedor {
    String cnpj;
    String nome;
    private UUID id;

    public Oferta criaOferta(Dinheiro custo, Integer estoque) {
        return new Oferta(this, custo, estoque);
    }

    public UUID getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }
}

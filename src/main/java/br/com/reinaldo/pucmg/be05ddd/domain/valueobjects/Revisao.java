package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

import java.util.UUID;

public class Revisao {
    private UUID id;
    private Cliente cliente;
    private String revisao;

    public Revisao(Cliente cliente, String revisao) {
        this.cliente = cliente;
        this.revisao = revisao;
    }

    public Revisao alterarRevisao(String revisao) {
        this.revisao = revisao;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getRevisao() {
        return revisao;
    }
}

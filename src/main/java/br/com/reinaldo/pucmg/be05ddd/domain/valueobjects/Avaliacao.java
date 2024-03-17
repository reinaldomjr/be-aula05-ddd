package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

import java.util.UUID;

public class Avaliacao {
    private UUID id;
    private Cliente cliente;
    private Integer nota;

    public Avaliacao(Cliente cliente, Integer nota) {
        this.cliente = cliente;
        this.nota = nota;
    }

    public void alterarNota(Integer nota) {
        this.nota = nota;
    }

    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getNota() {
        return nota;
    }
}

package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

public class Cliente {
    private String nome;
    private String sobrenome;

    public Cliente(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Revisao criarRevisao(String revisao) {
        return new Revisao(this, revisao);
    }

    public Avaliacao criarAvaliacao(Integer nota) {
        return new Avaliacao(this, nota);
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}

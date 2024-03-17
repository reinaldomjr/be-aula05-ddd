package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

public class Autor {
    private String Nome;
    private String Sobrenome;

    public Autor(String nome, String sobrenome) {
        Nome = nome;
        Sobrenome = sobrenome;
    }

    public String getNome() {
        return Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }
}

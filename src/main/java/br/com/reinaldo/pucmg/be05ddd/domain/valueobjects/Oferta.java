package br.com.reinaldo.pucmg.be05ddd.domain.valueobjects;

public class Oferta {
    private Vendedor vendedor;
    private Dinheiro preco;
    private Integer estoque;

    public Oferta(Vendedor vendedor, Dinheiro preco, Integer estoque) {
        this.vendedor = vendedor;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void adicionarEstoque(Integer estoqueAdicionado) {
        estoque += estoqueAdicionado;
    }

    public void definirEstoque(Integer quantidade) {
        this.estoque = quantidade;
    }

    public void definirPreco(Dinheiro preco) {
        this.preco = preco;
    }

    public void adicionarPreco(Dinheiro valorAdicional) {
        this.preco.adicionar(valorAdicional);

    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Dinheiro getPreco() {
        return preco;
    }

    public Integer getEstoque() {
        return estoque;
    }
}

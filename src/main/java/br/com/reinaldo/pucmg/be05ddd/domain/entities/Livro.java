package br.com.reinaldo.pucmg.be05ddd.domain.entities;

import br.com.reinaldo.pucmg.be05ddd.domain.valueobjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

public class Livro {
    private UUID id;
    private String titulo;
    private String isbn;
    private List<Autor> autores;
    private List<Livro> livrosRelacionados;
    private List<Revisao> revisoes = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    private List<Oferta> ofertas = new ArrayList<>();

    public Livro(String titulo, String isbn, List<Autor> autores, List<Livro> livrosRelacionados) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autores = autores;
        this.livrosRelacionados = livrosRelacionados;
    }

    public Revisao adicionarRevisao(Cliente cliente, String revisao) {
        boolean isRepeated = revisoes.stream()
                .filter(r -> r.getCliente().equals(cliente))
                .anyMatch(r -> r.getRevisao().equals(revisao));
        if (isRepeated) {
            throw new RuntimeException(format("Revisão já existe para o cliente %s", cliente.getNome()));
        }
        Revisao revisaoCriada = cliente.criarRevisao(revisao);
        revisoes.add(revisaoCriada);
        return revisaoCriada;

    }

    public boolean excluirRevisao(UUID idRevisao) {
        return revisoes.removeIf(revisao -> revisao.getId().equals(idRevisao));
    }

    public Avaliacao avaliar(Cliente cliente, Integer nota) {
        Optional<Avaliacao> avaliacao = avaliacoes.stream()
                .filter(a -> a.getCliente().equals(cliente))
                .findAny();

        if (avaliacao.isPresent()) {
            avaliacao.get().alterarNota(nota);
            return avaliacao.get();
        } else {
            avaliacoes.add(cliente.criarAvaliacao(nota));
            return null;
        }
    }

    public Oferta adicionarEstoque(UUID vendedor, Integer estoqueAdicionado) {
        Optional<Oferta> oferta = getOferta(vendedor);
        oferta.ifPresent(value -> value.adicionarEstoque(estoqueAdicionado));
        return oferta.orElseThrow(() -> new RuntimeException(format("Vendedor %s não possui Oferta para o livro %s", vendedor, this.getTitulo())));
    }

    public Integer obterEstoque(UUID vendedor) {
        return getOferta(vendedor).map(oferta -> oferta.getEstoque()).orElse(0);
    }

    public Oferta alterarPrecoOferta(UUID vendedor, Dinheiro preco) {

        Optional<Oferta> oferta = this.getOfertas().stream()
                .filter(o -> o.getVendedor().getId().equals(vendedor))
                .findAny();

        if (oferta.isPresent()) {
            oferta.get().definirPreco(preco);
            return oferta.get();
        } else {
            throw new RuntimeException("Oferta não encontrada");
        }
    }

    public Oferta criarOferta(Vendedor vendedor, Dinheiro preco, Integer quantidade) {

        Optional<Oferta> oferta = this.getOfertas().stream()
                .filter(o -> o.getVendedor().getId().equals(vendedor.getId()))
                .findAny();

        if (oferta.isPresent()) {
            throw new RuntimeException("Oferta já existe!");
        } else {
            Oferta novaOferta = vendedor.criaOferta(preco, quantidade);
            ofertas.add(novaOferta);
            return novaOferta;
        }
    }

    private Optional<Oferta> getOferta(UUID vendedor) {
        return getOfertas().stream()
                .filter(oferta -> oferta.getVendedor().getId().equals(vendedor))
                .findAny();
    }

    public Integer obterEstoqueTotal() {
        return getOfertas().stream()
                .mapToInt(Oferta::getEstoque)
                .sum();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<Livro> getLivrosRelacionados() {
        return livrosRelacionados;
    }

    public List<Revisao> getRevisoes() {
        return revisoes;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }
}

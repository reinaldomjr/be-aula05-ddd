package br.com.reinaldo.pucmg.be05ddd.application.services;

import br.com.reinaldo.pucmg.be05ddd.domain.entities.Livro;
import br.com.reinaldo.pucmg.be05ddd.domain.valueobjects.*;
import br.com.reinaldo.pucmg.be05ddd.domain.repositories.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {
    private LivrosRepository repository;

    public Livro adicionarLivro(String titulo, String isbn, List<Autor> autores, List<Livro> livrosRelacionados) {
        return repository.inserir(new Livro(titulo, isbn, autores, livrosRelacionados));
    }

    public boolean excluirLivro(UUID idLivro) {
        return repository.excluir(idLivro);
    }

    public Oferta criarOferta(UUID idLivro, Vendedor vendedor, Dinheiro preco, Integer quantidade) {
        Livro livro = buscarLivroPorID(idLivro);
        Oferta oferta = livro.criarOferta(vendedor, preco, quantidade);
        repository.salvar(livro);
        return oferta;
    }

    public Oferta alterarPrecoOferta(UUID idLivro, UUID idVendedor, Dinheiro preco) {
        Livro livro = buscarLivroPorID(idLivro);
        Oferta oferta = livro.alterarPrecoOferta(idVendedor, preco);
        repository.salvar(livro);
        return oferta;
    }

    public Oferta adicionarEstoque(UUID idLivro, UUID idVendedor, Integer quantidade) {
        Livro livro = buscarLivroPorID(idLivro);
        Oferta oferta = livro.adicionarEstoque(idVendedor, quantidade);
        repository.salvar(livro);
        return oferta;
    }

    private Livro buscarLivroPorID(UUID idLivro) {
        Optional<Livro> livro = repository.buscarPorID(idLivro);
        if (!livro.isPresent()) {
            throw new RuntimeException("Livro não encontrado!");
        }
        return livro.get();
    }

    public boolean excluirOferta(UUID idLivro, UUID idVendedor) {
        Livro livro = buscarLivroPorID(idLivro);
        return livro.getOfertas().removeIf(oferta -> oferta.getVendedor().equals(idVendedor));
    }

    public Integer obterEstoqueTotal(UUID idLivro) {
        Livro livro = buscarLivroPorID(idLivro);
        return livro.obterEstoqueTotal();
    }

    public Integer obterEstoque(UUID idLivro, UUID idVendedor) {
        Livro livro = buscarLivroPorID(idLivro);
        return livro.obterEstoque(idVendedor);
    }

    public Revisao adicionarRevisao(UUID idLivro, Cliente cliente, String revisao) {
        Livro livro = buscarLivroPorID(idLivro);
        Revisao revisaoCriada = livro.adicionarRevisao(cliente, revisao);
        repository.salvar(livro);
        return revisaoCriada;
    }

    public boolean excluirRevisao(UUID idLivro, UUID idRevisao) {
        Livro livro = buscarLivroPorID(idLivro);
        boolean excluido = livro.excluirRevisao(idRevisao);
        if (excluido) {
            repository.salvar(livro);
        }
        return excluido;
    }

    public Avaliacao avaliar(UUID idLivro, Cliente cliente, Integer nota) {
        Livro livro = buscarLivroPorID(idLivro);
        Avaliacao avaliacaoCriada = livro.avaliar(cliente, nota);
        repository.salvar(livro);
        return avaliacaoCriada;
    }
}

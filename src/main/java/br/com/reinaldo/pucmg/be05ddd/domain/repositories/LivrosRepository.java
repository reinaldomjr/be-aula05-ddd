package br.com.reinaldo.pucmg.be05ddd.domain.repositories;

import br.com.reinaldo.pucmg.be05ddd.domain.entities.Livro;

import java.util.Optional;
import java.util.UUID;

public interface LivrosRepository {
    Livro inserir(Livro livro);

    Optional<Livro> buscarPorID(UUID idLivro);

    boolean excluir(UUID idLivro);

    Livro salvar(Livro livro);
}

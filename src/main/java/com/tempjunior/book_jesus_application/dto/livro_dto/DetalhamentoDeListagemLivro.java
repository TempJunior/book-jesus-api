package com.tempjunior.book_jesus_application.dto.livro_dto;

import com.tempjunior.book_jesus_application.model.Categoria;
import com.tempjunior.book_jesus_application.model.Livro;

import java.time.Year;

public record DetalhamentoDeListagemLivro(
        Long id,
        String titulo,
        String autor,
        Categoria categoria,
        Year anoDePublicacao,
        Integer quantidadeEmEstoque
) {
    public DetalhamentoDeListagemLivro(Livro livro) {
        this(livro.getId(),livro.getTitulo(), livro.getAutor().getNome(), livro.getCategoria(), livro.getAnoDePublicacao(), livro.getQuantidadeEmEstoque());
    }
}

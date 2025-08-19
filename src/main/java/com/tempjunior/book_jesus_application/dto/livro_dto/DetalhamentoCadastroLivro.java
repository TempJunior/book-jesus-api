package com.tempjunior.book_jesus_application.dto.livro_dto;

import com.tempjunior.book_jesus_application.model.Categoria;
import com.tempjunior.book_jesus_application.model.Livro;

import java.time.Year;

public record DetalhamentoCadastroLivro(
        Long id,
        String titulo,
        Long autorId,
        Categoria categoria,
        Year anoDePublicacao,
        Integer quantidadeEmEstoque
) {
    public DetalhamentoCadastroLivro(Livro livro){
        this(livro.getId(), livro.getTitulo(), livro.getAutor().getId(), livro.getCategoria(), livro.getAnoDePublicacao(), livro.getQuantidadeEmEstoque());
    }
}

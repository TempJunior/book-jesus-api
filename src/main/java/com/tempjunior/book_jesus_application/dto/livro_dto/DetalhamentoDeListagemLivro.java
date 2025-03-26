package com.tempjunior.book_jesus_application.dto.livro_dto;

import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeListagemAutores;
import com.tempjunior.book_jesus_application.model.autor.Autor;
import com.tempjunior.book_jesus_application.model.categoria.Categoria;
import com.tempjunior.book_jesus_application.model.livro.Livro;

import java.time.Year;

public record DetalhamentoDeListagemLivro(
    String titulo,
    Autor autor,
    Categoria categoria,
    Year anoDePublicacao,
    Integer quantidadeEmEstoque
) {
    public DetalhamentoDeListagemLivro(Livro livro){
        this(livro.getTitulo(), livro.getAutor(), livro.getCategoria(), livro.getAnoDePublicacao(), livro.getQuantidadeEmEstoque());
    }
}

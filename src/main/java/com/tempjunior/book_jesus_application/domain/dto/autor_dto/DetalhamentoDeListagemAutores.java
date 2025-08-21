package com.tempjunior.book_jesus_application.domain.dto.autor_dto;

import com.tempjunior.book_jesus_application.domain.model.Autor;

import java.time.LocalDate;

public record DetalhamentoDeListagemAutores(
        String nome,
        String nacionalidade,
        LocalDate dataDeNascimento
) {
    public DetalhamentoDeListagemAutores(Autor autor) {
        this(autor.getNome(), autor.getNacionalidade(), autor.getDataDeNascimento());
    }
}

package com.tempjunior.book_jesus_application.dto.autor_dto;

import com.tempjunior.book_jesus_application.model.Autor;

import java.time.LocalDate;

public record DetalhamentoDeCadastroAutor(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataDeNascimento,
        boolean ativo
) {
    public DetalhamentoDeCadastroAutor(Autor autor){
        this(autor.getId(), autor.getNome(), autor.getNacionalidade(), autor.getDataDeNascimento(), autor.getAtivo());
    }
}

package com.tempjunior.book_jesus_application.domain.dto.autor_dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoAutor(
        @NotNull
        Long id,

        String nome,

        String nacionalidade,

        LocalDate dataDeNascimento
) {
}

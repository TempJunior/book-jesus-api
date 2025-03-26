package com.tempjunior.book_jesus_application.dto.autor_dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AutorCadastroDTO (

        @NotBlank
        String nome,

        @NotBlank
        String nacionalidade,

        @NotNull
                @Column(name = "data_de_nascimento")
        LocalDate dataDeNascimento
) {
}

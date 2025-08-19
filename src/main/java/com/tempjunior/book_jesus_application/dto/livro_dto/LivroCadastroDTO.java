package com.tempjunior.book_jesus_application.dto.livro_dto;

import com.tempjunior.book_jesus_application.model.Categoria;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Year;

public record LivroCadastroDTO(
        @NotBlank
        String titulo,

        @NotNull
                @Column(name = "autor_id")
        Long autorId,

        @NotNull
        Categoria categoria,

        @NotNull
        @Pattern(regexp = "\\d{4}")
        Year anoDePublicacao,

        @NotNull
        @Min(1)
        Integer quantidadeEmEstoque
) {
}

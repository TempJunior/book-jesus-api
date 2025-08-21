package com.tempjunior.book_jesus_application.domain.dto.usuario_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCadastroDTO(

        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotNull
        String telefone
) {
}

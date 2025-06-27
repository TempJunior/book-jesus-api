package com.tempjunior.book_jesus_application.dto.usuario_dto;

import jakarta.validation.constraints.NotNull;

public record DetalhesAtualizacaoUsuario(
        @NotNull
        Long id,
        String email,
        String telefone
) {
}

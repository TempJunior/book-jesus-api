package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import jakarta.validation.constraints.NotNull;

public record EmprestimoCadastroDTO(
        @NotNull
        Long idUsuario,
        @NotNull
        Long idLivro
) {
}

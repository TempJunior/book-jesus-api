package com.tempjunior.book_jesus_application.domain.dto.emprestimo_dto;

import jakarta.validation.constraints.NotNull;

public record EmprestimoCadastroDTO(
        @NotNull
        Long idUsuario,
        @NotNull(message = "Livro não pode estar null")
        Long idLivro
) {
}

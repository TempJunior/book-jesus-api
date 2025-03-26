package com.tempjunior.book_jesus_application.dto.livro_dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLivro(

        @NotNull
        Long id,

        Integer quantidadeEmEstoque
) {
}

package com.tempjunior.book_jesus_application.domain.dto.livro_dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLivro(

        @NotNull
        Long id,

        Integer quantidadeEmEstoque
) {
}

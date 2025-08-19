package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.model.Emprestimo;
import com.tempjunior.book_jesus_application.model.StatusEmprestimo;

import java.time.LocalDate;

public record DetalhamentoRegistroEmprestimo(
        Long id,
        String nomeUsuario,
        String titulosLivro,
        String autorNome,
        LocalDate data_emprestimo,
        LocalDate data_devolucao,
        StatusEmprestimo status
) {
    public DetalhamentoRegistroEmprestimo(Emprestimo dados){
        this(dados.getIdEmprestimo(), dados.getUsuario().getNome(), dados.getLivro().getTitulo(),dados.getLivro().getAutor().getNome(), dados.getData_emprestimo(), dados.getData_devolucao(), dados.getStatus());
    }
}

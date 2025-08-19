package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.model.Emprestimo;
import com.tempjunior.book_jesus_application.model.StatusEmprestimo;

import java.time.LocalDate;

public record DetalhamentoListagemEmprestimo(
        String usuario,
        String livro,
        String autor,
        LocalDate data_emprestimo,
        LocalDate data_devolucao,
        StatusEmprestimo statusEmprestimo
) {
    public DetalhamentoListagemEmprestimo(Emprestimo emprestimo){
        this(emprestimo.getUsuario().getNome(), emprestimo.getLivro().getTitulo(),emprestimo.getLivro().getAutor().getNome(), emprestimo.getData_emprestimo(), emprestimo.getData_devolucao(), emprestimo.getStatus());
    }
}

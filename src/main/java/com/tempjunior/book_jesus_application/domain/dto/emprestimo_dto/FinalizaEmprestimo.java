package com.tempjunior.book_jesus_application.domain.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.domain.model.Emprestimo;
import com.tempjunior.book_jesus_application.domain.model.StatusEmprestimo;

import java.time.LocalDate;

public record FinalizaEmprestimo(
        LocalDate data_devolucao,
        StatusEmprestimo status
) {
    public FinalizaEmprestimo(Emprestimo emprestimo){
        this(emprestimo.getData_devolucao(), emprestimo.getStatus());
    }
}

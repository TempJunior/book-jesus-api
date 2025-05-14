package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import com.tempjunior.book_jesus_application.model.emprestimo.StatusEmprestimo;

import java.time.LocalDate;

public record FinalizaEmprestimo(
        LocalDate data_devolucao,
        StatusEmprestimo status
) {
    public FinalizaEmprestimo(Emprestimo emprestimo){
        this(emprestimo.getData_devolucao(), emprestimo.getStatus());
    }
}

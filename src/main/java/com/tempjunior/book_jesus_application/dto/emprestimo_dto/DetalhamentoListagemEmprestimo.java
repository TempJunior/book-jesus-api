package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import com.tempjunior.book_jesus_application.model.emprestimo.StatusEmprestimo;
import com.tempjunior.book_jesus_application.model.livro.Livro;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;

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

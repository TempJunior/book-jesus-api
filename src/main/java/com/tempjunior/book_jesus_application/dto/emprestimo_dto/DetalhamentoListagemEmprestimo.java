package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import com.tempjunior.book_jesus_application.model.emprestimo.StatusEmprestimo;
import com.tempjunior.book_jesus_application.model.livro.Livro;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;

import java.time.LocalDate;

public record DetalhamentoListagemEmprestimo(
        Usuario id_usuario,
        Livro id_livro,
        LocalDate data_emprestimo,
        LocalDate data_devolucao,
        StatusEmprestimo statusEmprestimo
) {
    public DetalhamentoListagemEmprestimo(Emprestimo emprestimo){
        this(emprestimo.getUsuario(), emprestimo.getLivro(), emprestimo.getData_emprestimo(), emprestimo.getData_devolucao(), emprestimo.getStatus());
    }
}

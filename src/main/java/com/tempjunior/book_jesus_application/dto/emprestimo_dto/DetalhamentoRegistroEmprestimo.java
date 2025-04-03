package com.tempjunior.book_jesus_application.dto.emprestimo_dto;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import com.tempjunior.book_jesus_application.model.emprestimo.StatusEmprestimo;
import com.tempjunior.book_jesus_application.model.livro.Livro;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;

import java.time.LocalDate;

public record DetalhamentoRegistroEmprestimo(
        Long id,
        Usuario id_usuario,
        Livro id_livro,
        LocalDate data_emprestimo,
        LocalDate data_devolucao,
        StatusEmprestimo status
) {
    public DetalhamentoRegistroEmprestimo(Emprestimo dados){
        this(dados.getIdEmprestimo(), dados.getUsuario(), dados.getLivro(), dados.getData_emprestimo(), dados.getData_devolucao(), dados.getStatus());
    }

}

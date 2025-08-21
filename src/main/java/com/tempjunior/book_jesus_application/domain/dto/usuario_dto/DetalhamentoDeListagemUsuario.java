package com.tempjunior.book_jesus_application.domain.dto.usuario_dto;

import com.tempjunior.book_jesus_application.domain.model.Usuario;

import java.time.LocalDate;

public record DetalhamentoDeListagemUsuario(
        String nome,
        String email,
        LocalDate dataDeCadastro
) {
    public DetalhamentoDeListagemUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail(), usuario.getDataDeRegistro());
    }
}

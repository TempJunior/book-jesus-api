package com.tempjunior.book_jesus_application.dto.usuario_dto;

import com.tempjunior.book_jesus_application.model.usuario.Usuario;

import java.time.LocalDate;

public record DetalhamentoCadastroUsuario(
        Long id,
        String nome,
        String email,
        Long telefone,
        LocalDate dataDeRegistro
) {
    public DetalhamentoCadastroUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getDataDeRegistro());
    }
}

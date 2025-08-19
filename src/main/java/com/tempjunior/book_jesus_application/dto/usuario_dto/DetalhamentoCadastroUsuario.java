package com.tempjunior.book_jesus_application.dto.usuario_dto;

import com.tempjunior.book_jesus_application.model.Usuario;

import java.time.LocalDate;

public record DetalhamentoCadastroUsuario(
        Long id,
        String nome,
        String email,
        String telefone,
        LocalDate dataDeRegistro
) {
    public DetalhamentoCadastroUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getDataDeRegistro());
    }
}

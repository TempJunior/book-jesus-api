package com.tempjunior.book_jesus_application.infra.security.tokens;

public record DataTokenJWT(String token,Long id, String nome, String email, String telefone) {
}

package com.tempjunior.book_jesus_application.dto.usuario_dto.user_account;

import com.tempjunior.book_jesus_application.model.UserAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UserAccountRegisterDTO(
        @NotBlank
        String email,
        @NotBlank
        @Length(max = 250)
        String password,
        @NotBlank
        String name,
        @Pattern(regexp = "^\\d{2}\\d{8,9}$", message = "Telefone inválido, use DDD seguido do número")
        String telefone
) {

    public UserAccountRegisterDTO(UserAccount dados) {
        this(dados.getEmail(), dados.getPassword(), dados.getUser().getNome(), dados.getUser().getTelefone());
    }
}

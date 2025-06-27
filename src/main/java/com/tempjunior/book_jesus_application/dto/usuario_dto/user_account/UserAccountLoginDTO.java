package com.tempjunior.book_jesus_application.dto.usuario_dto.user_account;

import com.tempjunior.book_jesus_application.model.usuario.UserAccount;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserAccountLoginDTO(

        @NotBlank
        String email,
        @NotBlank
        @Length(max = 250)
        String password
) {
    public UserAccountLoginDTO(UserAccount dados){
        this(dados.getEmail(), dados.getPassword());
    }
}

package com.tempjunior.book_jesus_application.model.usuario;

import com.tempjunior.book_jesus_application.dto.usuario_dto.user_account.UserAccountRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario_account")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;

    public UserAccount(UserAccountRegisterDTO dados){
        this.email = dados.email();
        this.senha = dados.senha();
    }

}

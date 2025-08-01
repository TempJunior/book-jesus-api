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
    @Column(name = "senha")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario user;

    public UserAccount(UserAccountRegisterDTO dados) {
        this.email = dados.email();
        this.password = dados.password();
        this.user = new Usuario();
        this.user.setEmail(dados.email());
        this.user.setNome(dados.name());
        this.user.setTelefone(dados.telefone());
    }
}

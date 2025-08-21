package com.tempjunior.book_jesus_application.domain.model;

import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.DetalhesAtualizacaoUsuario;
import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.UsuarioCadastroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    @CreationTimestamp
    @Column(name = "data_de_registro")
    private LocalDate dataDeRegistro;

    public Usuario (UsuarioCadastroDTO dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
    }

    public void atualizarDados(DetalhesAtualizacaoUsuario dados){
        if (dados.email() != null){
            this.email = dados.email();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
    }
}

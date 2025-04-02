package com.tempjunior.book_jesus_application.model.emprestimo;

import com.tempjunior.book_jesus_application.model.livro.Livro;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_emprestimo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    @OneToMany
    @Column(name = "id_usuario")
    private Usuario idUsuario;

    @OneToMany
    @Column(name = "id_livro")
    private Livro idLivro;

    private LocalDate data_emprestimo;

    private LocalDate data_devolucao;

    private StatusEmprestimo status;
}

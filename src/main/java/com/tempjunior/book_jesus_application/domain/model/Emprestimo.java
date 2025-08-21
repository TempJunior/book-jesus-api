package com.tempjunior.book_jesus_application.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "tb_emprestimo")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @CreationTimestamp
    private LocalDate data_emprestimo;

    @Column(name = "data_de_devolucao")
    private LocalDate data_devolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEmprestimo status = StatusEmprestimo.ATIVO;


    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.status = StatusEmprestimo.ATIVO;
    }

    public void finalizarEmprestimo(Long id){
        this.data_devolucao = LocalDate.now();
        this.status = StatusEmprestimo.FINALIZADO;
    }

}

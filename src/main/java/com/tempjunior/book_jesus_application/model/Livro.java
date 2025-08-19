package com.tempjunior.book_jesus_application.model;

import com.tempjunior.book_jesus_application.dto.livro_dto.DadosAtualizacaoLivro;
import com.tempjunior.book_jesus_application.dto.livro_dto.LivroCadastroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Entity
@Table(name = "tb_livro")
@NoArgsConstructor
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(name = "ano_de_publicacao")
    private Year anoDePublicacao;

    @Column(name = "quantidade_em_estoque")
    private Integer quantidadeEmEstoque;

    //Livro recebe um LivroDTO - Usado na classe de Service para criar um novo Livro.
    public Livro (LivroCadastroDTO livroDados, Autor autorBanco){
        this.titulo = livroDados.titulo();
        this.autor = autorBanco;
        this.categoria = livroDados.categoria();
        this.anoDePublicacao = livroDados.anoDePublicacao();
        this.quantidadeEmEstoque = livroDados.quantidadeEmEstoque();
    }

    public void atualizar(DadosAtualizacaoLivro dados){
        if (dados.quantidadeEmEstoque() != null){
            this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
        }
    }

}

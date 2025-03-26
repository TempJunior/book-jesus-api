package com.tempjunior.book_jesus_application.model.autor;

import com.tempjunior.book_jesus_application.dto.autor_dto.AutorCadastroDTO;
import com.tempjunior.book_jesus_application.dto.autor_dto.DadosAtualizacaoAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nacionalidade;

    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    private Boolean ativo = true;

    public Autor (AutorCadastroDTO autorDados){
        this.nome = autorDados.nome();
        this.nacionalidade = autorDados.nacionalidade();
        this.dataDeNascimento = autorDados.dataDeNascimento();
    }

    public void atualizarDados(DadosAtualizacaoAutor dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.nacionalidade() != null){
            this.nacionalidade = dados.nacionalidade();
        }
        if (dados.dataDeNascimento() != null){
            this.dataDeNascimento = dados.dataDeNascimento();
        }
    }

    public void excluir (Autor autor){
        this.ativo = false;
    }
}

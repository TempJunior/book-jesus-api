package com.tempjunior.book_jesus_application.domain.service;

import com.tempjunior.book_jesus_application.domain.dto.livro_dto.DadosAtualizacaoLivro;
import com.tempjunior.book_jesus_application.domain.dto.livro_dto.DetalhamentoCadastroLivro;
import com.tempjunior.book_jesus_application.domain.dto.livro_dto.DetalhamentoDeListagemLivro;
import com.tempjunior.book_jesus_application.domain.dto.livro_dto.LivroCadastroDTO;
import com.tempjunior.book_jesus_application.infra.exceptions.LivroValidatorException;
import com.tempjunior.book_jesus_application.domain.model.Autor;
import com.tempjunior.book_jesus_application.domain.model.Livro;
import com.tempjunior.book_jesus_application.domain.repository.AutorRepository;
import com.tempjunior.book_jesus_application.domain.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public DetalhamentoCadastroLivro cadastrarNovoLivro(LivroCadastroDTO dados){
        Autor autor = autorRepository.findById(dados.autorId()).orElseThrow(() -> new LivroValidatorException("O autor não foi encontrado." +
                "Verifique se o autor existe e passe o ID correto."));

        Livro livro = new Livro(dados, autor);

        livroRepository.save(livro);

        return new DetalhamentoCadastroLivro(livro);
    }

    public Page<DetalhamentoDeListagemLivro> listarTodosLivros(Pageable paginacao){
        Pageable pageable = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), Sort.by("titulo"));

        var page = livroRepository.findAll(pageable).map(DetalhamentoDeListagemLivro::new);

        return page;
    }

    public DetalhamentoDeListagemLivro buscaPorId(Long id){
        Livro livroID = livroRepository.findById(id).orElseThrow(()
                -> new LivroValidatorException("Livro não encontrado"));

        return new DetalhamentoDeListagemLivro(livroID);
    }

    public DetalhamentoDeListagemLivro buscaPorNome(String nome){
        Livro livroNome = Optional.ofNullable(livroRepository.findByTitulo(nome))
                .orElseThrow(() -> new LivroValidatorException("Livro com o nome " + nome + "não encontrado."));

        return new DetalhamentoDeListagemLivro(livroNome);
    }

    public DetalhamentoCadastroLivro atualizaDadosLivro(DadosAtualizacaoLivro dados){
        var livro = livroRepository.getReferenceById(dados.id());

        livro.atualizar(dados);
        livroRepository.save(livro);

        return new DetalhamentoCadastroLivro(livro);
    }

    public void deletarLivro(Long id){
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroValidatorException("Livro não encontrado"));

        livroRepository.delete(livro);
    }
}

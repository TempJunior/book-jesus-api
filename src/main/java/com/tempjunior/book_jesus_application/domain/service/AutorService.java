package com.tempjunior.book_jesus_application.domain.service;

import com.tempjunior.book_jesus_application.domain.dto.autor_dto.AutorCadastroDTO;
import com.tempjunior.book_jesus_application.domain.dto.autor_dto.DadosAtualizacaoAutor;
import com.tempjunior.book_jesus_application.domain.dto.autor_dto.DetalhamentoDeCadastroAutor;
import com.tempjunior.book_jesus_application.domain.dto.autor_dto.DetalhamentoDeListagemAutores;
import com.tempjunior.book_jesus_application.infra.exceptions.AutorAssociadoException;
import com.tempjunior.book_jesus_application.infra.exceptions.AutorNaoEncontradoException;
import com.tempjunior.book_jesus_application.domain.model.Autor;
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
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public DetalhamentoDeCadastroAutor cadastrarNovoAutor(AutorCadastroDTO dados){
        Autor autor = new Autor(dados);
        autor = autorRepository.save(autor);
        return new DetalhamentoDeCadastroAutor(autor);
    }

    public Page<DetalhamentoDeListagemAutores> listarTodosAutores(Pageable paginacao) {
        // Criação de um Pageable com ordenação e tamanho padrão
        Pageable pageable = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), Sort.by("nome"));
        // Busca os autores paginados e mapeia para o DTO DetalhamentoDeListagemAutores
        var page = autorRepository.findAll(pageable).map(DetalhamentoDeListagemAutores::new);
        return page;
    }

    public DetalhamentoDeListagemAutores buscarAutorPorId(Long id){
        Autor autorId = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("O autor não foi encontrado." +
                "Verifique se o autor existe e passe o ID correto."));

        return new DetalhamentoDeListagemAutores(autorId);
    }

    public DetalhamentoDeListagemAutores buscarAutorPorNome(String nome){
        Autor autorNome = Optional.ofNullable(autorRepository.findByNome(nome))
                .orElseThrow(() -> new AutorNaoEncontradoException("O autor " + nome + " não foi encontrado. " +
                        "Verifique se o nome está correto ou cadastre esse autor."));

        return new DetalhamentoDeListagemAutores(autorNome);
    }

    @Transactional
    public DetalhamentoDeCadastroAutor atualizarDadosAutor(DadosAtualizacaoAutor dados){
        var autor = autorRepository.findById(dados.id())
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor com id " + dados.id() + " não encontrado"));

        autor.atualizarDados(dados);
        autorRepository.save(autor);

        return new DetalhamentoDeCadastroAutor(autor);
    }

    @Transactional
    public void deletarAutor(Long id){
        var autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));

        if (livroRepository.existsByAutor_id(id)){
            throw new AutorAssociadoException("Não é possivel excluir um autor com livros associados");
        }

        autorRepository.delete(autor);
    }

}

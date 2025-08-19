package com.tempjunior.book_jesus_application.service;

import com.tempjunior.book_jesus_application.dto.autor_dto.AutorCadastroDTO;
import com.tempjunior.book_jesus_application.dto.autor_dto.DadosAtualizacaoAutor;
import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeCadastroAutor;
import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeListagemAutores;
import com.tempjunior.book_jesus_application.infra.exceptions.AutorAssociadoException;
import com.tempjunior.book_jesus_application.infra.exceptions.AutorNaoEncontradoException;
import com.tempjunior.book_jesus_application.model.Autor;
import com.tempjunior.book_jesus_application.repository.AutorRepository;
import com.tempjunior.book_jesus_application.repository.LivroRepository;
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

    /**
     * Cadastra um novo autor e devolve um @detalhamentoDeCadastro que é um DTO.
     * O detalhamento é devolvido como JSON quando um novo autor é criado e é implementado na classe @AutorController.
     * Recebe como parametro um DTO de @AutorCadastroDTO com apenas os dados a serem cadastrados.
     * */

    @Transactional
    public DetalhamentoDeCadastroAutor cadastrarNovoAutor(AutorCadastroDTO dados){
        Autor autor = new Autor(dados);
        autor = autorRepository.save(autor);
        return new DetalhamentoDeCadastroAutor(autor);
    }

    /**
     * Retorna um Page de @DetalhamentoDeListagem que é um DTO com apenas informações a serem mostrada.
     * Recebe como parametro um Pageable. (É implementado a logica no controller da ordenação).
     * Retorna apenas o page de todos os autores listados fazendo o Casting para DetalhamentoDeLstagem.
    * */
    public Page<DetalhamentoDeListagemAutores> listarTodosAutores(Pageable paginacao) {
        // Criação de um Pageable com ordenação e tamanho padrão
        Pageable pageable = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), Sort.by("nome"));
        // Busca os autores paginados e mapeia para o DTO DetalhamentoDeListagemAutores
        var page = autorRepository.findAll(pageable).map(DetalhamentoDeListagemAutores::new);
        return page;
    }

    /**
     * Busca autores pelo ID e devolve um DTO de DetalhamentoDeListagem.
     * Usa um Optinional se achar o autor, retorna no metodo devolvendo um Autor no DetalhamentoDeListagem.
     * Se não, lança uma exception personalizada de @AutorNaoEncontradoException
    * */
    public DetalhamentoDeListagemAutores buscarAutorPorId(Long id){
        Autor autorId = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("O autor não foi encontrado." +
                "Verifique se o autor existe e passe o ID correto."));

        return new DetalhamentoDeListagemAutores(autorId);
    }

    /**
     * Busca autor por nome. (Verificar o metodo no controller.). O nome é passado por parametro.
     * Metodo @FindByNome. Criado no @AutorRepository, procura por nome do autor.
    * */
    public DetalhamentoDeListagemAutores buscarAutorPorNome(String nome){
        Autor autorNome = Optional.ofNullable(autorRepository.findByNome(nome))
                .orElseThrow(() -> new AutorNaoEncontradoException("O autor " + nome + " não foi encontrado. " +
                        "Verifique se o nome está correto ou cadastre esse autor."));

        return new DetalhamentoDeListagemAutores(autorNome);
    }

    /**
     * Atualiza dados do autor, parametro de autalização passado, recebe um @DadosAtualizacaoAutor,
     * que é um DTO para os dados que podem ser atualizado.
     * Procura um autor por referencia do ID dos dados passado, sendo obrigatorio passar o ID
     * do autor que será atualizado.
    * */
    @Transactional
    public DetalhamentoDeCadastroAutor atualizarDadosAutor(DadosAtualizacaoAutor dados){
        var autor = autorRepository.findById(dados.id())
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor com id " + dados.id() + " não encontrado"));

        autor.atualizarDados(dados);
        autorRepository.save(autor);

        return new DetalhamentoDeCadastroAutor(autor);
    }

    /**
    * Exclusão total do autor.
     * Recebe um ID que será passado para dois metodos como parametro.
     * Procura pelo autor, passando o @AutorRepository e encontra por id. Se não encontrar, lança uma exception.
     * Metodo @existsByAutor_id criado no @LivroRepository para verificação se o ID passado no parametro foi encontrado,
     * e se existe um livro associado a aquele ID. Se tiver, lança uma exception personalizada.
     * Se não, deleta o Autor.
    */
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

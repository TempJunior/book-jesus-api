package com.tempjunior.book_jesus_application.controller.autor_controller;

import com.tempjunior.book_jesus_application.dto.autor_dto.AutorCadastroDTO;
import com.tempjunior.book_jesus_application.dto.autor_dto.DadosAtualizacaoAutor;
import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeCadastroAutor;
import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeListagemAutores;
import com.tempjunior.book_jesus_application.model.autor.Autor;
import com.tempjunior.book_jesus_application.service.autor_service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    /**
    * Cadastrar um novo autor via metodo POST
    * JSON padrão para cadastrar um novo Autor
    *
	"nome":"Espiner barbosa",
	"nacionalidade":"Eureco",
	"dataDeNascimento":"1785-07-21"
    * Conforme o padrão de cadastrodo e POST:
    * Devolve um cabeçalho e Código HTTP: 201.
    * Formato do Corpo da Resposta: JSON com os dados do novo registro.
    * Cabeçalho Location: Contém a URL para acessar o recurso criado.
    *
    * O ID é gerenciado automaticamente pelo banco para cada Autor cadastrado
    */
    @PostMapping
    public ResponseEntity<DetalhamentoDeCadastroAutor> cadastrarRequest(@RequestBody @Valid AutorCadastroDTO dados, UriComponentsBuilder uriBuilder){
        var detalhamento = service.cadastrarNovoAutor(dados);

        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(detalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamento);
    }

    /**
    * Traz um pageable de todos autores cadastrados. Apenas 10 por pagina.
    * Para mudar a pagina precisa especificar na URL a page
    * */
    @GetMapping
    public ResponseEntity<Page<DetalhamentoDeListagemAutores>> listarTodosAutores(@PageableDefault (size = 10, sort = {"nome"}) Pageable paginacao){
        var page = service.listarTodosAutores(paginacao);

        return ResponseEntity.ok(page);
    }

    /**
        Busca um autor pelo id passado na URL com metodo GET
    */
    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoDeListagemAutores> buscarPorId(@PathVariable Long id){
        var autorId = service.buscarAutorPorId(id);

        return ResponseEntity.ok(autorId);
    }

    /**
    Busca um autor pelo nome, requisição feito por
    RequestParam - URL para acessar localhost:8080/autor/buscarPorNome?nome={nome}
    */
    @GetMapping("/buscarPorNome")
    public ResponseEntity<DetalhamentoDeListagemAutores> buscarPorNome(@RequestParam String nome){
        var autorNome = service.buscarAutorPorNome(nome);

        return ResponseEntity.ok(autorNome);
    }

    @PutMapping
    public ResponseEntity<DetalhamentoDeCadastroAutor> atualizarInformacoesAutor(@RequestBody DadosAtualizacaoAutor dados){
        var autor = service.atualizarDadosAutor(dados);

        return ResponseEntity.ok().body(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Autor> deletarAutor(@PathVariable Long id){
        service.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }


}

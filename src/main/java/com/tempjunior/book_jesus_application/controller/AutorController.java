package com.tempjunior.book_jesus_application.controller;

import com.tempjunior.book_jesus_application.dto.autor_dto.AutorCadastroDTO;
import com.tempjunior.book_jesus_application.dto.autor_dto.DadosAtualizacaoAutor;
import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeCadastroAutor;
import com.tempjunior.book_jesus_application.dto.autor_dto.DetalhamentoDeListagemAutores;
import com.tempjunior.book_jesus_application.model.Autor;
import com.tempjunior.book_jesus_application.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Autores", description = "EndPoints para Crud de Autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @Operation(summary = "Cria um novo autor", description = "Endpoint que recebe um objeto de AutorCadastroDTO e o salva no banco de dados.")
    @PostMapping
    public ResponseEntity<DetalhamentoDeCadastroAutor> cadastrarRequest(@RequestBody @Valid @Parameter(description = "Recebe String - nome, String - Nacionalidade, LocalDate - dataDeNascimento") AutorCadastroDTO dados, UriComponentsBuilder uriBuilder){
        var detalhamento = service.cadastrarNovoAutor(dados);

        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(detalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamento);
    }

    @Operation(summary = "Lista TODOS os autores", description = "Endpoint que lista todos os autores cadastrados e devolve um Pageable")
    @GetMapping
    public ResponseEntity<Page<DetalhamentoDeListagemAutores>> listarTodosAutores(@PageableDefault (size = 10, sort = {"nome"}) Pageable paginacao){
        var page = service.listarTodosAutores(paginacao);

        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Busca autores por ID", description = "Endpoint que busca autores pelo ID deles no banco de dados")
    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoDeListagemAutores> buscarPorId(@PathVariable Long id){
        var autorId = service.buscarAutorPorId(id);

        return ResponseEntity.ok(autorId);
    }

    @Operation(summary = "Busca autores por NOME", description = "Endpoint que busca autores pelo NOME deles no banco de dados")
    @GetMapping("/buscarPorNome")
    public ResponseEntity<DetalhamentoDeListagemAutores> buscarPorNome(@RequestParam @Parameter(description = "Recebe uma String do tipo nome do autor") String nome){
        var autorNome = service.buscarAutorPorNome(nome);

        return ResponseEntity.ok(autorNome);
    }

    @Operation(summary = "Atualizar informações permitidas de Autor", description = "Endpoint que atualiza informações permitidas dos autores cadastrados")
    @PutMapping
    public ResponseEntity<DetalhamentoDeCadastroAutor> atualizarInformacoesAutor(@RequestBody @Parameter(description = "Recebe um objeto do tipo DadosAtualizacaoAutor que pode " +
            "receber Long - id <- OBRIGATORIO, String - nome, String - nacionalidade, LocalDate - dataDeNascimento ") DadosAtualizacaoAutor dados){
        var autor = service.atualizarDadosAutor(dados);

        return ResponseEntity.ok().body(autor);
    }

    @Operation(summary = "Deleção logica de autor no banco", description = "Endpoint que deleta autor - Desativa - Do banco de dados. Não será mais listado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Autor> deletarAutor(@PathVariable @Parameter(description = "Recebe um Long - id que vai usar para desativar o autor") Long id){
        service.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }


}

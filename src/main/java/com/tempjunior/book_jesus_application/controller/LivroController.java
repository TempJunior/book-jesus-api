package com.tempjunior.book_jesus_application.controller;

import com.tempjunior.book_jesus_application.dto.livro_dto.DadosAtualizacaoLivro;
import com.tempjunior.book_jesus_application.dto.livro_dto.DetalhamentoCadastroLivro;
import com.tempjunior.book_jesus_application.dto.livro_dto.DetalhamentoDeListagemLivro;
import com.tempjunior.book_jesus_application.dto.livro_dto.LivroCadastroDTO;
import com.tempjunior.book_jesus_application.model.Livro;
import com.tempjunior.book_jesus_application.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<DetalhamentoCadastroLivro> cadastrarLivro(@RequestBody LivroCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        var detalhamento = service.cadastrarNovoLivro(dados);
        URI uri = uriBuilder.path("/livro/{id}").buildAndExpand(detalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamento);
    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoDeListagemLivro>> listarTodosLivros(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = service.listarTodosLivros(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoDeListagemLivro> buscarPorId(@PathVariable Long id) throws Exception {
        var livroID = service.buscaPorId(id);

        return ResponseEntity.ok(livroID);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<DetalhamentoDeListagemLivro> buscaPorNome(@RequestParam String nome) throws Exception {
        var livroNome = service.buscaPorNome(nome);

        return ResponseEntity.ok(livroNome);
    }

    @PutMapping
    public ResponseEntity<DetalhamentoCadastroLivro> atualizarInformacoes(@RequestBody DadosAtualizacaoLivro dados) {
        var livro = service.atualizaDadosLivro(dados);

        return ResponseEntity.ok().body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Livro> deleteLivro(@PathVariable Long id) throws Exception {
        service.deletarLivro(id);

        return ResponseEntity.noContent().build();
    }


}

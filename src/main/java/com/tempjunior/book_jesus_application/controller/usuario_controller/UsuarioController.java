package com.tempjunior.book_jesus_application.controller.usuario_controller;

import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhamentoCadastroUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhamentoDeListagemUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhesAtualizacaoUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.UsuarioCadastroDTO;
import com.tempjunior.book_jesus_application.service.usuario_service.UsuarioService;
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
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<DetalhamentoCadastroUsuario> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO dados, UriComponentsBuilder uriBuilder){
        var detalhamento = service.cadastrarNovoUsuario(dados);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(detalhamento.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamento);
    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoDeListagemUsuario>> listarTodosUsuarios(@PageableDefault (size = 10, sort = {"nome"})Pageable paginacao){
        var page = service.listarTodosUsuarios(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<DetalhamentoCadastroUsuario> atualizarDadosUsuario(@RequestBody DetalhesAtualizacaoUsuario dados){
        var user = service.atualizarUsuario(dados);

        return ResponseEntity.ok().body(user);
    }
}

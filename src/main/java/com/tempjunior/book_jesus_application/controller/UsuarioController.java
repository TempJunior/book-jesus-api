package com.tempjunior.book_jesus_application.controller;

import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.DetalhamentoCadastroUsuario;
import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.DetalhamentoDeListagemUsuario;
import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.DetalhesAtualizacaoUsuario;
import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.UsuarioCadastroDTO;
import com.tempjunior.book_jesus_application.domain.service.MailSenderService;
import com.tempjunior.book_jesus_application.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping
    public ResponseEntity<DetalhamentoCadastroUsuario> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO dados, UriComponentsBuilder uriBuilder) {
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

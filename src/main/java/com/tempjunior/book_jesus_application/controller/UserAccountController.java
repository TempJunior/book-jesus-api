package com.tempjunior.book_jesus_application.controller;

import com.tempjunior.book_jesus_application.dto.usuario_dto.user_account.UserAccountRegisterDTO;
import com.tempjunior.book_jesus_application.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UserAccountRegisterDTO dados, UriComponentsBuilder uriBuilder){
        var details = service.salvar(dados);
        URI uri = uriBuilder.path("/account/{id}").buildAndExpand(details.email()).toUri();

        return ResponseEntity.created(uri).body(details);
    }
}

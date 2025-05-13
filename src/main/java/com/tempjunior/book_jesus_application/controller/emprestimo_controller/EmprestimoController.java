package com.tempjunior.book_jesus_application.controller.emprestimo_controller;

import com.tempjunior.book_jesus_application.dto.emprestimo_dto.DetalhamentoRegistroEmprestimo;
import com.tempjunior.book_jesus_application.dto.emprestimo_dto.EmprestimoCadastroDTO;
import com.tempjunior.book_jesus_application.service.emprestimo_service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @PostMapping
    public ResponseEntity<DetalhamentoRegistroEmprestimo> registrarNovoEmprestimo(@RequestBody @Valid EmprestimoCadastroDTO dados, UriComponentsBuilder uriBuilder) throws Exception {
        var detalhamento = service.registrarNovoEmprestimo(dados);

        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(detalhamento.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamento);
    }
}

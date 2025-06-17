package com.tempjunior.book_jesus_application.controller.emprestimo_controller;

import com.tempjunior.book_jesus_application.dto.emprestimo_dto.DetalhamentoListagemEmprestimo;
import com.tempjunior.book_jesus_application.dto.emprestimo_dto.DetalhamentoRegistroEmprestimo;
import com.tempjunior.book_jesus_application.dto.emprestimo_dto.EmprestimoCadastroDTO;
import com.tempjunior.book_jesus_application.service.emprestimo_service.EmprestimoService;
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

    @GetMapping
    public ResponseEntity<Page<DetalhamentoListagemEmprestimo>> buscarTodosEmprestimos(@PageableDefault (size = 10, sort = {"usuario_id"})Pageable paginacao){
        var page = service.listarEmprestimos(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscaPorFiltro")
    public ResponseEntity<Page<DetalhamentoListagemEmprestimo>> buscarPorFiltro(
            @RequestParam(required = false) Long livroId,
            @RequestParam(required = false) Long usuarioId,
            Pageable pageable) throws Exception {

        Page<DetalhamentoListagemEmprestimo> resultado = service.buscarPorFiltro(livroId, usuarioId, pageable);

        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/finalizarEmprestimo")
    public ResponseEntity<DetalhamentoRegistroEmprestimo> finalizarEmprestimo(@RequestParam Long id) throws Exception {
        var detalhamento = service.finalizarEmprestimo(id);

        return ResponseEntity.ok(detalhamento);
    }
}

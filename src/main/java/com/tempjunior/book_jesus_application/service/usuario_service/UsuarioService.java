package com.tempjunior.book_jesus_application.service.usuario_service;

import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhamentoCadastroUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhamentoDeListagemUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.UsuarioCadastroDTO;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;
import com.tempjunior.book_jesus_application.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public DetalhamentoCadastroUsuario cadastrarNovoUsuario(UsuarioCadastroDTO dados){
        var usuario = new Usuario(dados);

        usuario = repository.save(usuario);

        return new DetalhamentoCadastroUsuario(usuario);
    }

    public Page<DetalhamentoDeListagemUsuario> listarTodosUsuarios(Pageable paginacao){
        Pageable pageable = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), Sort.by("nome"));

        var page = repository.findAll(pageable).map(DetalhamentoDeListagemUsuario::new);

        return page;
    }

    public
}

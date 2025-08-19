package com.tempjunior.book_jesus_application.service;

import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhamentoCadastroUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhamentoDeListagemUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.DetalhesAtualizacaoUsuario;
import com.tempjunior.book_jesus_application.dto.usuario_dto.UsuarioCadastroDTO;
import com.tempjunior.book_jesus_application.model.Usuario;
import com.tempjunior.book_jesus_application.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
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

    @Transactional
    public DetalhamentoCadastroUsuario atualizarUsuario(DetalhesAtualizacaoUsuario dados){
        var user = repository.getReferenceById(dados.id());
        user.atualizarDados(dados);
        repository.save(user);

        return new DetalhamentoCadastroUsuario(user);
    }

    @Transactional
    public void deletarUsuario(Long id) throws Exception {
        var user = repository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));

        //Implementar logica de Deletar um usuário (somente se não houver empréstimos pendentes).

    }
}

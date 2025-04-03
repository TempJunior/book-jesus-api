package com.tempjunior.book_jesus_application.service.emprestimo_service;

import com.tempjunior.book_jesus_application.dto.emprestimo_dto.DetalhamentoRegistroEmprestimo;
import com.tempjunior.book_jesus_application.dto.emprestimo_dto.EmprestimoCadastroDTO;
import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import com.tempjunior.book_jesus_application.model.livro.Livro;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;
import com.tempjunior.book_jesus_application.repository.EmprestimoRepository;
import com.tempjunior.book_jesus_application.repository.LivroRepository;
import com.tempjunior.book_jesus_application.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public DetalhamentoRegistroEmprestimo registrarNovoEmprestimo(EmprestimoCadastroDTO dados){
        Usuario usuario = usuarioRepository.findById(dados.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(dados.idLivro())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo(usuario, livro);

        emprestimo = emprestimoRepository.save(emprestimo);

        return new DetalhamentoRegistroEmprestimo(emprestimo);
    }
}

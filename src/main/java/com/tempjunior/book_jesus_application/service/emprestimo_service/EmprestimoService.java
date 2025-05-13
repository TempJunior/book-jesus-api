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
    public DetalhamentoRegistroEmprestimo registrarNovoEmprestimo(EmprestimoCadastroDTO dados) throws Exception {
        Usuario usuario = usuarioRepository.findById(dados.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        long emprestimosAtivos = emprestimoRepository.countEmprestimosAtivos(usuario.getId());
        if (emprestimosAtivos >= 3) {
            throw new Exception("Usuario já possui 3 emprestimos.");
        }

        Livro livro = livroRepository.findByIdForUpdate(dados.idLivro())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getQuantidadeEmEstoque() <= 0) {
            throw new Exception("Não temos livros em estoque.");
        }

        livro.setQuantidadeEmEstoque(livro.getQuantidadeEmEstoque() - 1);
        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimo = emprestimoRepository.save(emprestimo);

        return new DetalhamentoRegistroEmprestimo(emprestimo);
    }
}

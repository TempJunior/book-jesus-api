package com.tempjunior.book_jesus_application.service.emprestimo_service;

import com.tempjunior.book_jesus_application.dto.emprestimo_dto.DetalhamentoListagemEmprestimo;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            throw new Exception("Usuário já possui 3 empréstimos.");
        }

        Livro livro = livroRepository
                .findByIdForUpdate(dados.idLivro())
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

    public Page<DetalhamentoListagemEmprestimo> listarEmprestimos(Pageable paginacao){
        Pageable pageable = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), Sort.by("usuario"));

        var page = emprestimoRepository.findAll(pageable).map(DetalhamentoListagemEmprestimo::new);

        return page;
    }

    public Page<DetalhamentoListagemEmprestimo> buscarPorFiltro(Long livroId, Long usuarioId, Pageable pageable) throws Exception {
        // Se o id do livro for informado, busque a entidade correspondente
        Livro livro = null;
        if (livroId != null) {
            livro = livroRepository.findById(livroId)
                    .orElseThrow(() -> new Exception("Livro não encontrado."));
        }

        // Se o id do usuário for informado, busque a entidade correspondente
        Usuario usuario = null;
        if (usuarioId != null) {
            usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new Exception("Usuário não encontrado."));
        }

        // Busca os empréstimos de acordo com os filtros e a paginação
        Page<Emprestimo> emprestimos = emprestimoRepository.findByFiltro(livro, usuario, pageable);

        // Converte cada Emprestimo para o DTO DetalhamentoListagemEmprestimo
        return emprestimos.map(DetalhamentoListagemEmprestimo::new);
    }

    @Transactional
    public DetalhamentoRegistroEmprestimo finalizarEmprestimo(Long id) throws Exception {
        var livro = emprestimoRepository.findById(id)
                .orElseThrow(() -> new Exception("Emprestimo não encontrado"));
        livro.finalizarEmprestimo(id);
        emprestimoRepository.save(livro);

        return new DetalhamentoRegistroEmprestimo(livro);
    }
}

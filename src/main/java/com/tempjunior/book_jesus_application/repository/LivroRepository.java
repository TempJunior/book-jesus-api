package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByAutor_id(Long id);
    Livro findByTitulo(String nome);
}

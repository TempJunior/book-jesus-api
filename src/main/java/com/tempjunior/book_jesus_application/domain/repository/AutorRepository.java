package com.tempjunior.book_jesus_application.domain.repository;

import com.tempjunior.book_jesus_application.domain.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome (String nome);
}

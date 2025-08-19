package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome (String nome);
}

package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.livro.Livro;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByAutor_id(Long id);
    Livro findByTitulo(String nome);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT l FROM Livro l WHERE l.id = :id")
    Optional<Livro> findByIdForUpdate(@Param("id") Long id);

}

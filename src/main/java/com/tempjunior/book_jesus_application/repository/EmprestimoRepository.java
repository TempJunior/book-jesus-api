package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import com.tempjunior.book_jesus_application.model.livro.Livro;
import com.tempjunior.book_jesus_application.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT e FROM Emprestimo e " +
            "WHERE (:livro IS NULL OR e.livro = :livro) " +
            "AND (:usuario IS NULL OR e.usuario = :usuario)")
    Page<Emprestimo> findByFiltro(@Param("livro") Livro livro,
                                  @Param("usuario") Usuario usuario,
                                  Pageable pageable);

}

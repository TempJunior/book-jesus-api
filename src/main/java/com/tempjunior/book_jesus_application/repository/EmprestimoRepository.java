package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {


    @Query("""
      SELECT COUNT(e)
        FROM Emprestimo e 
       WHERE e.usuario.id = :usuarioId 
         AND e.dataDevolucao IS NULL
    """)
    long countEmprestimosAtivos(@Param("usuarioId") Long usuarioId);

}

package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.emprestimo.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}

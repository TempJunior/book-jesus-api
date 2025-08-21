package com.tempjunior.book_jesus_application.domain.repository;

import com.tempjunior.book_jesus_application.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

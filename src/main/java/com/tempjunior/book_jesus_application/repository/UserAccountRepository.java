package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.usuario.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}

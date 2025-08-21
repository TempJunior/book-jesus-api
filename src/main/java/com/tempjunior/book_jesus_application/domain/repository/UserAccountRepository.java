package com.tempjunior.book_jesus_application.domain.repository;

import com.tempjunior.book_jesus_application.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}

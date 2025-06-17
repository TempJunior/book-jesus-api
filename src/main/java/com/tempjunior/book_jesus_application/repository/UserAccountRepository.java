package com.tempjunior.book_jesus_application.repository;

import com.tempjunior.book_jesus_application.model.usuario.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserDetails findByEmail(String email);
}

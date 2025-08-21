package com.tempjunior.book_jesus_application.domain.service;

import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.user_account.UserAccountRegisterDTO;
import com.tempjunior.book_jesus_application.domain.model.UserAccount;
import com.tempjunior.book_jesus_application.domain.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserAccountRepository repository;

    @Transactional
    public UserAccountRegisterDTO salvar(UserAccountRegisterDTO user){
        var userAccount = new UserAccount(user);
        String hash = encoder.encode(userAccount.getPassword());
        userAccount.setPassword(hash);
        repository.save(userAccount);

        return new UserAccountRegisterDTO(userAccount);
    }
}

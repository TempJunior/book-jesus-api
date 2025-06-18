package com.tempjunior.book_jesus_application.service.usuario_service.user_account_service;

import com.tempjunior.book_jesus_application.dto.usuario_dto.user_account.UserAccountRegisterDTO;
import com.tempjunior.book_jesus_application.model.usuario.UserAccount;
import com.tempjunior.book_jesus_application.repository.UserAccountRepository;
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

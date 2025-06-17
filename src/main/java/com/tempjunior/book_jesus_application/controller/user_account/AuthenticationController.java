package com.tempjunior.book_jesus_application.controller.user_account;

import com.tempjunior.book_jesus_application.dto.usuario_dto.user_account.UserAccountRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid UserAccountRegisterDTO userDto){
        var token = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.senha());
        var auth = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}

package com.tempjunior.book_jesus_application.controller.user_account;

import com.tempjunior.book_jesus_application.dto.usuario_dto.user_account.UserAccountLoginDTO;
import com.tempjunior.book_jesus_application.dto.usuario_dto.user_account.UserAccountRegisterDTO;
import com.tempjunior.book_jesus_application.infra.security.tokens.DataTokenJWT;
import com.tempjunior.book_jesus_application.infra.security.tokens.TokenService;
import com.tempjunior.book_jesus_application.model.usuario.UserAccount;
import com.tempjunior.book_jesus_application.model.usuario.UserDetailsImp;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid UserAccountLoginDTO userDto){
        var authToken = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());
        var auth = manager.authenticate(authToken);
        var userDetails = (UserDetailsImp) auth.getPrincipal();
        var usuario = userDetails.getUserAccount();

        var token = tokenService.generatedToken(userDetails.getUserAccount());

        return ResponseEntity.ok(new DataTokenJWT(token, usuario.getId(),usuario.getUser().getNome(), usuario.getEmail(), usuario.getUser().getTelefone()));
    }
}

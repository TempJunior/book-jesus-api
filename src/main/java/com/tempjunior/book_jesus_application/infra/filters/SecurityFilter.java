package com.tempjunior.book_jesus_application.infra.filters;

import com.tempjunior.book_jesus_application.infra.security.tokens.TokenService;
import com.tempjunior.book_jesus_application.model.UserDetailsImp;
import com.tempjunior.book_jesus_application.repository.UserAccountRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAccountRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = responseToken(request);

        if (tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var optionalUser = repository.findByEmail(subject);
            if (optionalUser.isPresent()){
                var userAccount = optionalUser.get();
                var userDetailImp = new UserDetailsImp(userAccount);

                var auth = new UsernamePasswordAuthenticationToken(userDetailImp, null, userDetailImp.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }

        filterChain.doFilter(request, response);
    }

    private String responseToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}

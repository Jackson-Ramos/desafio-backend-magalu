package com.jcode_development.magalums.controller;

import com.jcode_development.magalums.model.user.AccountCredentials;
import com.jcode_development.magalums.model.user.AccountRegister;
import com.jcode_development.magalums.model.user.User;
import com.jcode_development.magalums.repository.UserRepository;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Getter
    private User loggedUser;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody AccountCredentials credentials) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(credentials.login(), credentials.password());
        var authentication = authenticationManager.authenticate(usernamePassword);

        if (authentication.isAuthenticated()) {
            loggedUser = (User) userRepository.loadUserByLogin(credentials.login());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AccountRegister register) {

        if (userRepository.loadUserByLogin(register.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new User(null, register.login(), encryptedPassword, register.permissions());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}

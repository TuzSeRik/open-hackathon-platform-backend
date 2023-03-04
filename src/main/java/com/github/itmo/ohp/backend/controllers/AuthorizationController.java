package com.github.itmo.ohp.backend.controllers;

import com.github.itmo.ohp.backend.model.User;
import com.github.itmo.ohp.backend.requests.RegistrationRequest;
import com.github.itmo.ohp.backend.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/user") @RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    @PostMapping("/register")
    public Mono<ResponseEntity<User>> registerUser(@RequestBody RegistrationRequest request) {
        User user = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        
        return authorizationService.addUser(user)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK));
    }
}

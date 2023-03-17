package com.github.itmo.ohp.backend.services;

import com.github.itmo.ohp.backend.model.User;
import com.github.itmo.ohp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service @RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    
    public Mono<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Mono<User> addUser(User user) {
        user.setAuthorities("ROLE_USER");
        return userRepository.save(user);
    }
    
    public Mono<User> addOrg(User user) {
        user.setAuthorities("ROLE_USER,ROLE_ORG");
        return userRepository.save(user);
    }
}

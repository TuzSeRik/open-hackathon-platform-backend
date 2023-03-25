package com.github.itmo.ohp.backend.authorization.module.services;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import com.github.itmo.ohp.backend.authorization.module.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service @RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    
    public Mono<UserModel> findUser(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Mono<UserModel> addUser(UserModel user) {
        user.setAuthorities("ROLE_USER");
        return userRepository.save(user);
    }
    
    public Mono<UserModel> addOrg(UserModel user) {
        user.setAuthorities("ROLE_USER,ROLE_ORG");
        return userRepository.save(user);
    }
    
}

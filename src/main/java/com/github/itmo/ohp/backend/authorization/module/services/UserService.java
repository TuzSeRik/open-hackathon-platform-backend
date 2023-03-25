package com.github.itmo.ohp.backend.authorization.module.services;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import com.github.itmo.ohp.backend.authorization.module.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    public Flux<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Flux<UserModel> getAllUsersForTeam(UUID teamId) {
        return userRepository.findAllByTeamId(teamId);
    }
    
    public Mono<UserModel> getUserById(UUID id) {
        return userRepository.findById(id);
    }
    
    public Mono<UserModel> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Mono<UserModel> saveUser(UserModel user) {
        return userRepository.save(user);
    }
    
    public Mono<UserModel> saveUserAsUser(UserModel user) {
        user.setAuthorities("ROLE_USER");
        return userRepository.save(user);
    }
    
    public Mono<UserModel> saveUserAsOrganizer(UserModel user) {
        user.setAuthorities("ROLE_USER,ROLE_ORG");
        return userRepository.save(user);
    }
    
    public Mono<UserModel> updateUser(UUID id, UserModel user) {
        return userRepository.findById(id)
                .flatMap(changingUser -> {
                    changingUser.setUsername(user.getUsername());
                    changingUser.setPassword(user.getPassword());
                    changingUser.setAuthorities(user.getAuthorities());
                    changingUser.setTeamId(user.getTeamId());
                    return userRepository.save(changingUser);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<UserModel> deleteUser(UUID id) {
        Mono<UserModel> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return user;
    }
    
    public Flux<UserModel> deleteAllUsers() {
        Flux<UserModel> users = userRepository.findAll();
        userRepository.deleteAll();
        return users;
    }
    
    public Flux<UserModel> deleteAllUsersForTeam(UUID teamId) {
        Flux<UserModel> results = userRepository.findAllByTeamId(teamId);
        userRepository.deleteAllByTeamId(teamId);
        return results;
    }
    
}

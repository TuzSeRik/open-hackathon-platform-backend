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
        user.encodePassword();
        return userRepository.save(user);
    }
    
    public Mono<UserModel> createUser(UserModel user) {
        user.setAuthorities("ROLE_USER");
        user.encodePassword();
        return userRepository.save(user);
    }
    
    public Mono<UserModel> createOrganizer(UserModel user) {
        user.setAuthorities("ROLE_USER,ROLE_ORG");
        user.encodePassword();
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
        return userRepository
                .findById(id)
                .flatMap(user -> userRepository.deleteById(user.getId()).thenReturn(user));
    }
    
    public Flux<UserModel> deleteAllUsers() {
        return userRepository
                .findAll()
                .collectList()
                .flatMap((users) -> userRepository.deleteAll().thenReturn(users))
                .flatMapMany(Flux::fromIterable);
    }
    
    public Flux<UserModel> deleteAllUsersForTeam(UUID teamId) {
        return userRepository.findAll()
                .collectList()
                .flatMap((users) -> userRepository.deleteAllByTeamId(teamId).thenReturn(users))
                .flatMapMany(Flux::fromIterable);
    }
    
}

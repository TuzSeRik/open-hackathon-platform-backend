package com.github.itmo.ohp.backend.authorization.module.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DatabaseReactiveUserDetailsService implements ReactiveUserDetailsService {
    private final UserService userService;
    
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userService.getUserByUsername(username).flatMap(user -> Mono.just(
            new User(
                user.getUsername(),
                user.getPassword(),
                Arrays.stream(user.getAuthorities().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableSet())
            )
        ));
    }
    
}

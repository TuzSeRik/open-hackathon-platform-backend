package com.github.itmo.ohp.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DatabaseReactiveUserDetailsService implements ReactiveUserDetailsService {
    private final AuthorizationService authorizationService;
    private boolean alreadySet;
    @Value("{administrator.username}")
    private String username;
    @Value("{administrator.password}")
    private String password;
    
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        if (!alreadySet) {
            authorizationService.addUser(new com.github.itmo.ohp.backend.model.User(
                    UUID.randomUUID(),
                    username,
                    new BCryptPasswordEncoder().encode(password),
                    "ROLE_USER;ROLE_ADMIN"
            ));
            
            alreadySet = true;
        }
    }
    
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return authorizationService.findUser(username).flatMap(user -> Mono.just(
            new User(
                user.getUsername(),
                user.getPassword(),
                Arrays.stream(user.getAuthorities().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableSet())
            )
        ));
    }
}

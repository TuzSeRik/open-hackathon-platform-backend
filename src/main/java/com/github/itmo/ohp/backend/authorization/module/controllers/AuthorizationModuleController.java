package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import com.github.itmo.ohp.backend.authorization.module.requests.SimpleCreateUserRequest;
import com.github.itmo.ohp.backend.authorization.module.responses.UserResponse;
import com.github.itmo.ohp.backend.authorization.module.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/actions/authorization") @RequiredArgsConstructor
public class AuthorizationModuleController {
    // Place for specific, "short-cutting" actions in the module
    private final UserService userService;
    
    @PostMapping("/user")
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody SimpleCreateUserRequest request) {
        UserModel user = UserModel.builder()
                .teamId(new UUID(0L, 0L))
                .username(request.username())
                .password(request.password())
                .authorities("")
        .build();
        
        return userService.createUser(user)
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/organizer")
    public Mono<ResponseEntity<UserResponse>> createOrganizer(@RequestBody SimpleCreateUserRequest request) {
        UserModel user = UserModel.builder()
                .teamId(new UUID(0L, 0L))
                .username(request.username())
                .password(request.password())
                .authorities("")
        .build();
        
        return userService.createOrganizer(user)
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}

package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import com.github.itmo.ohp.backend.authorization.module.requests.CreateUserRequest;
import com.github.itmo.ohp.backend.authorization.module.responses.UserResponse;
import com.github.itmo.ohp.backend.authorization.module.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/organizer") @RequiredArgsConstructor
public class OrganizerController {
    private final UserService userService;
    
    @PostMapping
    public Mono<ResponseEntity<UserResponse>> createOrganizer(@RequestBody CreateUserRequest request) {
        UserModel user = UserModel.builder()
                .teamId(request.teamId())
                .username(request.username())
                .password(request.password())
                .authorities(request.authorities())
        .build();
        
        return userService.saveUserAsOrganizer(user)
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}

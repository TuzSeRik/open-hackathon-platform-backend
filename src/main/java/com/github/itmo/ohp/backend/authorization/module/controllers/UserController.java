package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.requests.CreateUserRequest;
import com.github.itmo.ohp.backend.authorization.module.requests.UpdateUserRequest;
import com.github.itmo.ohp.backend.authorization.module.responses.AllUsersResponse;
import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import com.github.itmo.ohp.backend.authorization.module.responses.UserResponse;
import com.github.itmo.ohp.backend.authorization.module.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/user") @RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping
    public Mono<ResponseEntity<AllUsersResponse>> getAllUsers() {
        return userService.getAllUsers()
                .map(UserResponse::fromUserModel).collectList()
                .map(AllUsersResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllUsersResponse>> getAllUsersForTeam(@PathVariable("teamId") UUID teamId) {
        return userService.getAllUsersForTeam(teamId)
                .map(UserResponse::fromUserModel).collectList()
                .map(AllUsersResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> getUser(@AuthenticationPrincipal UserDetails userDetails,
                                                      @PathVariable("id") UUID id) {
        Mono<UserModel> user;
        
        if (id.equals(new UUID(0L, 0L))) {
            user = userService.getUserByUsername(userDetails.getUsername());
        }
        else {
            user = userService.getUserById(id);
        }
        
        return user
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody CreateUserRequest request) {
        UserModel user = UserModel.builder()
                .teamId(request.teamId())
                .username(request.username())
                .password(request.password())
                .authorities(request.authorities())
        .build();
        
        return userService.saveUserAsUser(user)
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> updateUser(@PathVariable("id") UUID id,
                                                         @RequestBody UpdateUserRequest request) {
        UserModel user = UserModel.builder()
                .teamId(request.teamId())
                .username(request.username())
                .password(request.password())
                .authorities(request.authorities())
        .build();
        
        return userService.updateUser(id, user)
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> deleteUser(@PathVariable("id") UUID id) {
        return userService.deleteUser(id)
                .map(UserResponse::fromUserModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllUsersResponse>> deleteAllUsers() {
        return userService.deleteAllUsers()
                .map(UserResponse::fromUserModel).collectList()
                .map(AllUsersResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllUsersResponse>> deleteAllUsersForTeam(@PathVariable("teamId") UUID teamId) {
        return userService.deleteAllUsersForTeam(teamId)
                .map(UserResponse::fromUserModel).collectList()
                .map(AllUsersResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}

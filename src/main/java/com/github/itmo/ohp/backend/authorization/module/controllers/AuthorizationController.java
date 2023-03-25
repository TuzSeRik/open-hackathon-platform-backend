package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.responses.AuthResponse;
import com.github.itmo.ohp.backend.authorization.module.requests.RegistrationRequest;
import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import com.github.itmo.ohp.backend.authorization.module.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController @RequestMapping("/user") @RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    
    @PostMapping("/auth")
    public Mono<ResponseEntity<AuthResponse>> authUser(@AuthenticationPrincipal UserDetails userDetails) {
        assert userDetails != null;
        
        return Mono.just(new ResponseEntity<>(
                new AuthResponse()
                        .setIsAuthorized(true)
                        .setAuthorities(userDetails.getAuthorities()
                                .stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())),
                HttpStatus.OK)
        );
    }
    
    @PostMapping("/register")
    public Mono<ResponseEntity<AuthResponse>> registerUser(@RequestBody RegistrationRequest request) {
        UserModel user = new UserModel().setUsername(request.getUsername()).setPassword(request.getPassword());
        
        return authorizationService.addUser(user)
                .map(u -> {
                            assert u != null;
                            return new ResponseEntity<>(
                                        new AuthResponse()
                                            .setIsAuthorized(true)
                                            .setAuthorities(Arrays.asList(u.getAuthorities().split(","))),
                                    HttpStatus.OK);
                        }
                );
    }
    
    @PostMapping("/org")
    public Mono<ResponseEntity<AuthResponse>> registerOrg(@RequestBody RegistrationRequest request) {
        UserModel user = new UserModel().setUsername(request.getUsername()).setPassword(request.getPassword());
        
        return authorizationService.addOrg(user)
                .map(u -> {
                            assert u != null;
                            return new ResponseEntity<>(
                                    new AuthResponse()
                                            .setIsAuthorized(true)
                                            .setAuthorities(Arrays.asList(u.getAuthorities().split(","))),
                                    HttpStatus.OK);
                        }
                );
    }
    
}

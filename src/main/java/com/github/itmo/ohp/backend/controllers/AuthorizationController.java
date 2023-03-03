package com.github.itmo.ohp.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController @RequestMapping("/")
public class AuthorizationController {
    @GetMapping("/register")
    private String registerUser(@RequestParam String login, @RequestParam String password) {
        
        return login;
    }
    
    @GetMapping("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}

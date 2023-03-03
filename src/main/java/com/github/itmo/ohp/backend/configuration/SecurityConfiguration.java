package com.github.itmo.ohp.backend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration @EnableWebFluxSecurity @RequiredArgsConstructor
public class SecurityConfiguration {
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//            .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(authorize -> authorize
                .pathMatchers("/main/**").permitAll()
                .anyExchange().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
}

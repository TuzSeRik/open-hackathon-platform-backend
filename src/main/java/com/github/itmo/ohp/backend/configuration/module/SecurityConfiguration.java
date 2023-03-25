package com.github.itmo.ohp.backend.configuration.module;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import java.util.List;

@EnableWebFluxSecurity @Configuration @RequiredArgsConstructor
public class SecurityConfiguration {
    public final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//            .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(authorize -> authorize
                    .pathMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                    .pathMatchers("/user/org").hasAuthority("ROLE_ADMIN")
                    .pathMatchers("/user/**").permitAll()
                    .pathMatchers(HttpMethod.GET, "/information/public").permitAll()
                    .pathMatchers(HttpMethod.POST, "/information/**").hasAuthority("ROLE_ORG")
                    
                    // Hackathon module start
                    .pathMatchers(HttpMethod.GET, "/api/hackathon/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.POST, "/api/hackathon/**").hasAuthority("ROLE_ADMIN")
                    .pathMatchers(HttpMethod.PUT, "/api/hackathon/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.DELETE, "/api/hackathon/**").hasAuthority("ROLE_ADMIN")
        
                    .pathMatchers(HttpMethod.GET, "/api/stage/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.POST, "/api/stage/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.PUT, "/api/stage/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.DELETE, "/api/stage/**").hasAuthority("ROLE_ORG")
                    
                    .pathMatchers(HttpMethod.GET, "/api/task/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.POST, "/api/task/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.PUT, "/api/task/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.DELETE, "/api/task/**").hasAuthority("ROLE_ORG")
                    // Hackathon module end
                    
                    .anyExchange().authenticated()
            ).httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:4173"));
        configuration.setAllowedMethods(List.of("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }
    
}

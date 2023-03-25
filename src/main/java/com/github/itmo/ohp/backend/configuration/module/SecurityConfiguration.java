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
                    
                    // Authorization module start
                    .pathMatchers(HttpMethod.GET, "/api/team/**").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/team/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.PUT, "/api/team/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.DELETE, "/api/team/**").hasAuthority("ROLE_ORG")
                    
                    .pathMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                    .pathMatchers(HttpMethod.PUT, "/api/user/**").permitAll()
                    .pathMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority("ROLE_ORG")
                    
                    .pathMatchers(HttpMethod.POST, "/api/organizer/**").hasAuthority("ROLE_ADMIN")
                    
                    .pathMatchers(HttpMethod.GET, "/api/invite/**").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/invite/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.PUT, "/api/invite/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.DELETE, "/api/invite/**").hasAuthority("ROLE_ORG")
                    // Authorization module end
                    
                    // Information module start
                    .pathMatchers(HttpMethod.GET, "/api/actions/information/public").permitAll()
                    .pathMatchers(HttpMethod.GET, "/api/actions/information/private").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.PUT, "/api/actions/information/public").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.PUT, "/api/actions/information/private").hasAuthority("ROLE_ORG")
                    
                    .pathMatchers(HttpMethod.GET, "/api/information/**").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/information/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.PUT, "/api/information/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.DELETE, "/api/information/**").hasAuthority("ROLE_ADMIN")
                    // Information module end
                    
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
                    
                    .pathMatchers(HttpMethod.GET, "/api/task-problem/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.POST, "/api/task-problem/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.PUT, "/api/task-problem/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.DELETE, "/api/task-problem/**").hasAuthority("ROLE_ORG")
                    
                    .pathMatchers(HttpMethod.GET, "/api/result/**").hasAuthority("ROLE_USER")
                    .pathMatchers(HttpMethod.POST, "/api/result/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.PUT, "/api/result/**").hasAuthority("ROLE_ORG")
                    .pathMatchers(HttpMethod.DELETE, "/api/result/**").hasAuthority("ROLE_ORG")
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

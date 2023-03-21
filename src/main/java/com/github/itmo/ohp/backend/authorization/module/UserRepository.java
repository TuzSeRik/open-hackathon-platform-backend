package com.github.itmo.ohp.backend.authorization.module;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<UserModel, UUID> {
    Mono<UserModel> findByUsername(String username);
    
}

package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
    Mono<User> findByUsername(String username);
}

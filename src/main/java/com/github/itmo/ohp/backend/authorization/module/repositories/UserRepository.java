package com.github.itmo.ohp.backend.authorization.module.repositories;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<UserModel, UUID> {
    Mono<UserModel> findByUsername(String username);
    
}

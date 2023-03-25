package com.github.itmo.ohp.backend.authorization.module.repositories;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserModel, UUID> {
    Mono<UserModel> findByUsername(String username);
    Flux<UserModel> findAllByTeamId(UUID teamId);
    Flux<Void> deleteAllByTeamId(UUID teamId);
    
}

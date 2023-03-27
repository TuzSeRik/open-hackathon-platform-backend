package com.github.itmo.ohp.backend.authorization.module.repositories;

import com.github.itmo.ohp.backend.authorization.module.models.InviteModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Repository
public interface InviteRepository extends ReactiveCrudRepository<InviteModel, UUID> {
    Flux<InviteModel> findAllByTeamId(UUID teamId);
    Flux<Void> deleteAllByTeamId(UUID teamId);
    
}

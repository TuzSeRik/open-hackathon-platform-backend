package com.github.itmo.ohp.backend.authorization.module.repositories;
import com.github.itmo.ohp.backend.authorization.module.models.InviteModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface InviteRepository extends ReactiveCrudRepository<InviteModel, UUID> {

    Mono<InviteModel> findByTeamId(UUID teamId);

}

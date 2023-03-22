package com.github.itmo.ohp.backend.team.module.repositories;
import com.github.itmo.ohp.backend.team.module.model.InviteModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface InviteRepository extends ReactiveCrudRepository<InviteModel, UUID> {

    Mono<InviteModel> findByTeamId(UUID teamId);

}

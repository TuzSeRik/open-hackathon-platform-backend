package com.github.itmo.ohp.backend.repositories;
import com.github.itmo.ohp.backend.model.Invite;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface InviteRepository extends ReactiveCrudRepository<Invite, UUID> {

    Mono<Invite> findByTeamId(UUID teamId);

}

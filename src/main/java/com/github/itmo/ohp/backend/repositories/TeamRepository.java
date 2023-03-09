package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.Team;
import com.github.itmo.ohp.backend.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public interface TeamRepository extends ReactiveCrudRepository<Team, UUID> {
}

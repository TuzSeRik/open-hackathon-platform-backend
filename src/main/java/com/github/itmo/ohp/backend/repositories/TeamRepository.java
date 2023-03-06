package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.Team;
import com.github.itmo.ohp.backend.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface TeamRepository extends ReactiveCrudRepository<Team, UUID> {
}

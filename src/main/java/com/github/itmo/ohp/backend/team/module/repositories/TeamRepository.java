package com.github.itmo.ohp.backend.team.module.repositories;

import com.github.itmo.ohp.backend.team.module.models.TeamModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface TeamRepository extends ReactiveCrudRepository<TeamModel, UUID> {
}

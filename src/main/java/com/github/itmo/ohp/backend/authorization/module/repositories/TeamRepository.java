package com.github.itmo.ohp.backend.authorization.module.repositories;

import com.github.itmo.ohp.backend.authorization.module.models.TeamModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TeamRepository extends ReactiveCrudRepository<TeamModel, UUID> {

}

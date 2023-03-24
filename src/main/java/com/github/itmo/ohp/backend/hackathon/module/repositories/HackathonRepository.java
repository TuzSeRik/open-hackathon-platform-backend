package com.github.itmo.ohp.backend.hackathon.module.repositories;

import com.github.itmo.ohp.backend.hackathon.module.models.HackathonModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import java.util.UUID;

public interface HackathonRepository extends ReactiveCrudRepository<HackathonModel, UUID> {

}

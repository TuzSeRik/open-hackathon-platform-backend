package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.Hackathon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface HackathonRepository extends ReactiveCrudRepository<Hackathon, UUID> {

}

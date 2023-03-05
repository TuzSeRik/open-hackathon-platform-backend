package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.Hackathon;
import com.github.itmo.ohp.backend.model.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ResultRepository extends ReactiveCrudRepository<Result, UUID> {
}

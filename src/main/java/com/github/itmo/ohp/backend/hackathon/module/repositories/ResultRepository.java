package com.github.itmo.ohp.backend.hackathon.module.repositories;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ResultRepository extends ReactiveCrudRepository<ResultModel, UUID> {
}

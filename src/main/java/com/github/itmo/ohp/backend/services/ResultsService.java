package com.github.itmo.ohp.backend.services;

import com.github.itmo.ohp.backend.model.Result;
import com.github.itmo.ohp.backend.model.User;
import com.github.itmo.ohp.backend.repositories.HackathonRepository;
import com.github.itmo.ohp.backend.repositories.ResultRepository;
import com.github.itmo.ohp.backend.requests.results.UserResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResultsService {
    private final ResultRepository resultRepository;
    private final HackathonRepository hackathonRepository;

    public Mono<Result> sendResult(UserResultRequest userResultRequest, UUID hackathonId) {
        //TODO: validate result
        Result result = new Result();
        result.setLink(userResultRequest.getLink());
        result.setHackathonId(hackathonId);
        result.setUserId(userResultRequest.getUserId());
        return resultRepository.save(result);
    }
}

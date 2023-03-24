package com.github.itmo.ohp.backend.hackathon.module.services;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import com.github.itmo.ohp.backend.hackathon.module.repositories.HackathonRepository;
import com.github.itmo.ohp.backend.hackathon.module.repositories.ResultRepository;
import com.github.itmo.ohp.backend.hackathon.module.requests.UserResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResultsService {
    private final ResultRepository resultRepository;
    private final HackathonRepository hackathonRepository;

    public Mono<ResultModel> sendResult(UserResultRequest userResultRequest, UUID hackathonId) {
        //TODO: validate result
        ResultModel result = new ResultModel();
        result.setLink(userResultRequest.getLink());
        result.setHackathonId(hackathonId);
        result.setUserId(userResultRequest.getUserId());
        return resultRepository.save(result);
    }
}

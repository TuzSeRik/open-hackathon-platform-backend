package com.github.itmo.ohp.backend.hackathon.module;

import com.github.itmo.ohp.backend.hackathon.module.requests.HackathonPhaseTimerRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.MessageRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.UserResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/results",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ResultsController {

    private final ResultsService resultsService;

    // send result with hackathon id and user result
    @PostMapping("/{hid}")
    public ResponseEntity<?> sendResult(@PathVariable UUID hid, @RequestBody UserResultRequest userResultRequest) {
        return new ResponseEntity<>(resultsService.sendResult(userResultRequest, hid), HttpStatus.OK);
    }

    // get results with hackathon id
    @GetMapping("/{id}")
    public ResponseEntity<?> getResults(@PathVariable UUID id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // update hackaton's start date
    @PutMapping("/update/{id}")
    public ResponseEntity<?> changeHackathonStartDate(@PathVariable UUID id, @RequestBody String hackathonRequest) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // create phases for hackathon
    @PostMapping("/create/{id}")
    public ResponseEntity<?> createHackathonPhase(@PathVariable UUID id,
                                                  @RequestBody HackathonPhaseTimerRequest hackathonPhaseRequest) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // get hackathon's phase with hackathon id ant phase id
    @GetMapping("/phase/{hid}/{id}")
    public ResponseEntity<?> getHackathonPhase(@PathVariable UUID hid, @PathVariable UUID id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/support")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/admin/support")
    public ResponseEntity<?> sendResponseToMessage(@RequestBody MessageRequest messageRequest) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

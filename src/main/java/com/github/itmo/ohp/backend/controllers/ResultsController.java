package com.github.itmo.ohp.backend.controllers;

import com.github.itmo.ohp.backend.requests.results.UserResultRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/results",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ResultsController {

    @PostMapping("/{id}")
    public ResponseEntity<?> sendResult(@PathVariable UUID id, @RequestBody UserResultRequest userResult) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }



}

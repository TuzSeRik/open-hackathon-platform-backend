package com.github.itmo.ohp.backend.hackathon.module.controllers;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskProblemModel;
import com.github.itmo.ohp.backend.hackathon.module.requests.CreateTaskProblemRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.UpdateTaskProblemRequest;
import com.github.itmo.ohp.backend.hackathon.module.responses.AllTaskProblemsResponse;
import com.github.itmo.ohp.backend.hackathon.module.responses.TaskProblemResponse;
import com.github.itmo.ohp.backend.hackathon.module.services.TaskProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/task-problem") @RequiredArgsConstructor
public class TaskProblemController {
    private final TaskProblemService taskProblemService;
    
    @GetMapping
    public Mono<ResponseEntity<AllTaskProblemsResponse>> getAllTaskProblems() {
        return taskProblemService.getAllTaskProblems()
                .map(TaskProblemResponse::fromTaskProblemModel).collectList()
                .map(AllTaskProblemsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/task/{taskId}")
    public Mono<ResponseEntity<AllTaskProblemsResponse>> getAllTaskProblemsForTask(@PathVariable("taskId") UUID taskId) {
        return taskProblemService.getAllTaskProblemsForTask(taskId)
                .map(TaskProblemResponse::fromTaskProblemModel).collectList()
                .map(AllTaskProblemsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskProblemResponse>> getTaskProblem(@PathVariable("id") UUID id) {
        return taskProblemService.getTaskProblemById(id)
                .map(TaskProblemResponse::fromTaskProblemModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<TaskProblemResponse>> createTaskProblem(@RequestBody CreateTaskProblemRequest request) {
        TaskProblemModel taskProblem = TaskProblemModel.builder()
                .taskId(request.taskId())
                .type(request.type())
                .problem(request.problem())
        .build();
        
        return taskProblemService.saveTaskProblem(taskProblem)
                .map(TaskProblemResponse::fromTaskProblemModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskProblemResponse>> updateTaskProblem(@PathVariable("id") UUID id,
                                                                       @RequestBody UpdateTaskProblemRequest request) {
        TaskProblemModel taskProblem = TaskProblemModel.builder()
                .taskId(request.taskId())
                .type(request.type())
                .problem(request.problem())
        .build();
        
        return taskProblemService.updateTaskProblem(id, taskProblem)
                .map(TaskProblemResponse::fromTaskProblemModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<TaskProblemResponse>> deleteTaskProblem(@PathVariable("id") UUID id) {
        return taskProblemService.deleteTaskProblem(id)
                .map(TaskProblemResponse::fromTaskProblemModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllTaskProblemsResponse>> deleteAllTaskProblems() {
        return taskProblemService.deleteAllTaskProblems()
                .map(TaskProblemResponse::fromTaskProblemModel).collectList()
                .map(AllTaskProblemsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/task/{taskId}")
    public Mono<ResponseEntity<AllTaskProblemsResponse>> deleteAllTaskProblemsForTask(@PathVariable("taskId") UUID taskId) {
        return taskProblemService.deleteAllTaskProblemsForTask(taskId)
                .map(TaskProblemResponse::fromTaskProblemModel).collectList()
                .map(AllTaskProblemsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}

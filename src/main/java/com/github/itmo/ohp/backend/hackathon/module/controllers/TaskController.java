package com.github.itmo.ohp.backend.hackathon.module.controllers;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskModel;
import com.github.itmo.ohp.backend.hackathon.module.requests.CreateTaskRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.UpdateTaskRequest;
import com.github.itmo.ohp.backend.hackathon.module.responses.AllTasksResponse;
import com.github.itmo.ohp.backend.hackathon.module.responses.TaskResponse;
import com.github.itmo.ohp.backend.hackathon.module.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/task") @RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    
    @GetMapping
    public Mono<ResponseEntity<AllTasksResponse>> getAllTasks() {
        return taskService.getAllTasks()
                .map(TaskResponse::fromTaskModel).collectList()
                .map(AllTasksResponse::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/stage/{stageId}")
    public Mono<ResponseEntity<AllTasksResponse>> getAllTasksForStage(@PathVariable("stageId") UUID stageId) {
        return taskService.getAllTasksForStage(stageId)
                .map(TaskResponse::fromTaskModel).collectList()
                .map(AllTasksResponse::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> getTask(@PathVariable("id") UUID id) {
        return taskService.getTaskById(id)
                .map(TaskResponse::fromTaskModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<TaskResponse>> createTask(@RequestBody CreateTaskRequest request) {
        TaskModel task = TaskModel.builder()
                .stageId(request.stageId())
                .isReady(false)
                .build();
        
        return taskService.saveTask(task)
                .map(TaskResponse::fromTaskModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> updateTask(@PathVariable("id") UUID id,
                                                           @RequestBody UpdateTaskRequest request) {
        TaskModel task = TaskModel.builder()
                .stageId(request.stageId())
                .isReady(request.isReady())
                .build();
        
        return taskService.updateTask(id, task)
                .map(TaskResponse::fromTaskModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> deleteTask(@PathVariable("id") UUID id) {
        return taskService.deleteTask(id)
                .map(TaskResponse::fromTaskModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllTasksResponse>> deleteAllTasks() {
        return taskService.deleteAllTasks()
                .map(TaskResponse::fromTaskModel).collectList()
                .map(AllTasksResponse::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/stage/{stageId}")
    public Mono<ResponseEntity<AllTasksResponse>> deleteAllTasksForStage(@PathVariable("stageId") UUID stageId) {
        return taskService.deleteAllTasksForStage(stageId)
                .map(TaskResponse::fromTaskModel).collectList()
                .map(AllTasksResponse::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}

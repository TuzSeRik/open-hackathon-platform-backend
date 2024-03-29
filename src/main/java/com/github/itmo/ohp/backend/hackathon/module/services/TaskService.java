package com.github.itmo.ohp.backend.hackathon.module.services;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskModel;
import com.github.itmo.ohp.backend.hackathon.module.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    
    public Flux<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Flux<TaskModel> getAllTasksForStage(UUID stageId) {
        return taskRepository.findAllByStageId(stageId);
    }
    
    public Mono<TaskModel> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }
    
    public Mono<TaskModel> saveTask(TaskModel task) {
        return taskRepository.save(task);
    }
    
    public Mono<TaskModel> updateTask(UUID id, TaskModel task) {
        return taskRepository.findById(id)
                .flatMap(changingTask -> {
                    changingTask.setStageId(task.getStageId());
                    changingTask.setIsReady(task.getIsReady());
                    return taskRepository.save(changingTask);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<TaskModel> deleteTask(UUID id) {
        return taskRepository
                .findById(id)
                .flatMap(task -> taskRepository.deleteById(task.getId()).thenReturn(task));
    }
    
    public Flux<TaskModel> deleteAllTasks() {
        return taskRepository
                .findAll()
                .collectList()
                .flatMap((tasks) -> taskRepository.deleteAll().thenReturn(tasks))
                .flatMapMany(Flux::fromIterable);
    }
    
    
    public Flux<TaskModel> deleteAllTasksForStage(UUID stageId) {
        return taskRepository
                .findAllByStageId(stageId)
                .collectList()
                .flatMap((tasks) -> taskRepository.deleteAllByStageId(stageId).thenReturn(tasks))
                .flatMapMany(Flux::fromIterable);
    }
    
}

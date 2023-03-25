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
        Mono<TaskModel> task = taskRepository.findById(id);
        taskRepository.deleteById(id);
        return task;
    }
    
    public Flux<TaskModel> deleteAllTasks() {
        Flux<TaskModel> tasks = taskRepository.findAll();
        taskRepository.deleteAll();
        return tasks;
    }
    
    
    public Flux<TaskModel> deleteAllTasksForStage(UUID stageId) {
        Flux<TaskModel> tasks = taskRepository.findAllByStageId(stageId);
        taskRepository.deleteAllByStageId(stageId);
        return tasks;
    }
    
}

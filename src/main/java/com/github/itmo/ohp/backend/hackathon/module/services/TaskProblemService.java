package com.github.itmo.ohp.backend.hackathon.module.services;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskProblemModel;
import com.github.itmo.ohp.backend.hackathon.module.repositories.TaskProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class TaskProblemService {
    private final TaskProblemRepository taskProblemRepository;
    
    public Flux<TaskProblemModel> getAllTaskProblems() {
        return taskProblemRepository.findAll();
    }
    
    public Flux<TaskProblemModel> getAllTaskProblemsForTask(UUID taskId) {
        return taskProblemRepository.findAllByTaskId(taskId);
    }
    
    public Mono<TaskProblemModel> getTaskProblemById(UUID id) {
        return taskProblemRepository.findById(id);
    }
    
    public Mono<TaskProblemModel> saveTaskProblem(TaskProblemModel taskProblem) {
        return taskProblemRepository.save(taskProblem);
    }
    
    public Mono<TaskProblemModel> updateTaskProblem(UUID id, TaskProblemModel taskProblem) {
        return taskProblemRepository.findById(id)
                .flatMap(changingTaskProblem -> {
                    changingTaskProblem.setTaskId(taskProblem.getTaskId());
                    changingTaskProblem.setType(taskProblem.getType());
                    changingTaskProblem.setProblem(taskProblem.getProblem());
                    return taskProblemRepository.save(changingTaskProblem);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<TaskProblemModel> deleteTaskProblem(UUID id) {
        Mono<TaskProblemModel> taskProblem = taskProblemRepository.findById(id);
        return taskProblemRepository.deleteById(id).then(taskProblem);
    }
    
    public Flux<TaskProblemModel> deleteAllTaskProblems() {
        Flux<TaskProblemModel> taskProblems = taskProblemRepository.findAll();
        return taskProblemRepository.deleteAll().thenMany(taskProblems);
    }
    
    public Flux<TaskProblemModel> deleteAllTaskProblemsForTask(UUID taskId) {
        Flux<TaskProblemModel> taskProblems = taskProblemRepository.findAllByTaskId(taskId);
        return taskProblemRepository.deleteAllByTaskId(taskId).thenMany(taskProblems);
    }
    
}

package com.react.react.controller;

import com.react.react.model.Task;
import com.react.react.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public void create(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @GetMapping
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}

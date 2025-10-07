package com.coveragex.CoveragexTaskApp.controller;

import com.coveragex.CoveragexTaskApp.model.Task;
import com.coveragex.CoveragexTaskApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getRecentTasks();
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Integer id) {
        return taskService.updateTaskStatus(id);
    }
}

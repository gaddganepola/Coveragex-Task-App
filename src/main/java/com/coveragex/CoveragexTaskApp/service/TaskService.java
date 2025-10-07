package com.coveragex.CoveragexTaskApp.service;

import com.coveragex.CoveragexTaskApp.model.Task;
import com.coveragex.CoveragexTaskApp.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public ResponseEntity<Task> createTask(Task task) {
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity<>(taskRepo.save(task), HttpStatus.OK);
    }

    public ResponseEntity<List<Task>> getRecentTasks() {
        return new ResponseEntity<>(taskRepo.findRecentTasks(), HttpStatus.OK);
    }

    public ResponseEntity<Task> updateTaskStatus(Integer id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(true);
            taskRepo.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

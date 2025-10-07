package com.coveragex.CoveragexTaskApp.service;

import com.coveragex.CoveragexTaskApp.model.Task;
import com.coveragex.CoveragexTaskApp.repo.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task inputTask = new Task();
        inputTask.setTitle("Test Task");
        inputTask.setDescription("This is a test task");

        Task savedTask = new Task(1, "Test Task", "This is a test task", false, LocalDateTime.now());
        when(taskRepo.save(any(Task.class))).thenReturn(savedTask);

        ResponseEntity<Task> response = taskService.createTask(inputTask);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isCompleted());
        verify(taskRepo, times(1)).save(any(Task.class));
    }

    @Test
    void testGetRecentTasks() {
        List<Task> mockTasks = List.of(
                new Task(1, "Task 1", "Desc", false, LocalDateTime.now())
        );
        when(taskRepo.findRecentTasks()).thenReturn(mockTasks);

        ResponseEntity<List<Task>> response = taskService.getRecentTasks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(taskRepo, times(1)).findRecentTasks();
    }

    @Test
    void testUpdateTaskStatus_TaskFound() {
        Task task = new Task(1, "Task 1", "Desc", false, LocalDateTime.now());
        when(taskRepo.findById(1)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        ResponseEntity<Task> response = taskService.updateTaskStatus(1);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isCompleted());
        verify(taskRepo, times(1)).save(task);
    }

    @Test
    void testUpdateTaskStatus_TaskNotFound() {
        when(taskRepo.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskService.updateTaskStatus(99);

        assertEquals(404, response.getStatusCodeValue());
        verify(taskRepo, never()).save(any(Task.class));
    }
}

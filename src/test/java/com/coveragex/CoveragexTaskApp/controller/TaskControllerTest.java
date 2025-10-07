package com.coveragex.CoveragexTaskApp.controller;

import com.coveragex.CoveragexTaskApp.model.Task;
import com.coveragex.CoveragexTaskApp.repo.TaskRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTaskEndpoint() throws Exception {
        Task newTask = new Task();
        newTask.setTitle("Integration Test Task");
        newTask.setDescription("Testing controller create");

        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Test Task"))
                .andExpect(jsonPath("$.completed").value(false));    }

    @Test
    void testGetAllTasksEndpoint() throws Exception {
        taskRepo.save(new Task(null, "Sample Task", "Description", false, LocalDateTime.now()));

        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testUpdateTaskStatusEndpoint() throws Exception {
        Task task = taskRepo.save(new Task(null, "Task to Complete", "Test", false, LocalDateTime.now()));

        mockMvc.perform(put("/task/" + task.getTaskId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }
}

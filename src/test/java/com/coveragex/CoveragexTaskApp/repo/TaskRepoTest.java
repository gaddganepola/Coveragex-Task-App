package com.coveragex.CoveragexTaskApp.repo;

import com.coveragex.CoveragexTaskApp.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TaskRepoTest {

    @Autowired
    private TaskRepo taskRepo;

    @Test
    void testFindRecentTasks() {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("For testing");
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);

        taskRepo.save(task); // ✅ Table will now exist

        List<Task> tasks = taskRepo.findAll();
        assertFalse(tasks.isEmpty());
    }
}

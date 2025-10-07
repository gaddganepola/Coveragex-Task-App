package com.coveragex.CoveragexTaskApp.repo;

import com.coveragex.CoveragexTaskApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.isCompleted = false ORDER BY t.createdAt DESC LIMIT 5")
    List<Task> findRecentTasks();
}

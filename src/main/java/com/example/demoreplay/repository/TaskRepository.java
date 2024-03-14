package com.example.demoreplay.repository;

import com.example.demoreplay.entity.Task;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.jpa.HibernateHints.*;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true, value = "select * from tasks")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "100"),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = HINT_READ_ONLY, value = "true"),
    })
    Stream<Task> getAllTasks();

    List<Task> findAllByIsDeadlinedNotAndDueDateIsBefore(String value, LocalDateTime now);
}

package com.example.demoreplay.repository;

import com.example.demoreplay.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

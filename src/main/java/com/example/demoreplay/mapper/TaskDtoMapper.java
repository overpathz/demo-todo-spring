package com.example.demoreplay.mapper;

import com.example.demoreplay.dto.TaskDto;
import com.example.demoreplay.entity.Task;
import org.springframework.stereotype.Component;

import java.security.KeyStore;

@Component
public class TaskDtoMapper {
    public TaskDto mapToDto(Task task) {
        return new TaskDto(task.getName(), task.getDescription(), task.getCreatedAt());
    }
}

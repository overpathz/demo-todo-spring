package com.example.demoreplay.service;

import com.example.demoreplay.dto.ReportDto;
import com.example.demoreplay.entity.Task;
import com.example.demoreplay.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {
    public ReportDto createReport(User user, long lastMin) {
        List<Task> tasks = user.getTasks();
        LocalDateTime minus5minDate = LocalDateTime.now().minusMinutes(lastMin);
        long count = tasks.stream().filter(task -> task.getCreatedAt().isAfter(minus5minDate)).count();
        return new ReportDto(user.getUsername(), lastMin, count);
    }
}

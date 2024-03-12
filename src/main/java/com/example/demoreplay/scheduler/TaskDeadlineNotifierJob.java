package com.example.demoreplay.scheduler;

import com.example.demoreplay.entity.Task;
import com.example.demoreplay.repository.TaskRepository;
import com.example.demoreplay.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j

public class TaskDeadlineNotifierJob {
    private final TaskRepository taskRepository;
    private final EmailService emailService;

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.SECONDS)
    public void checkDueDate() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            if (!task.getIsDeadlined().equals("true")) {
                LocalDateTime now = LocalDateTime.now();
                if (task.getDueDate().isBefore(now)) {
                    task.setIsDeadlined("true");
                    taskRepository.save(task);
                    emailService.sendEmail(task.getUser().getEmail(), "Your task '"+task.getName()+"' is deadlined", "Please, check your tasks and complete them");
                }
            }
        }
    }
}

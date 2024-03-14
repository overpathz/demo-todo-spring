package com.example.demoreplay.scheduler;

import com.example.demoreplay.entity.Task;
import com.example.demoreplay.repository.TaskRepository;
import com.example.demoreplay.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j

public class TaskDeadlineNotifierJob {
    private final TaskRepository taskRepository;
    private final EmailService emailService;

//    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.SECONDS)
    public void checkDueDate() {
        long start = System.currentTimeMillis(); // now
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findAllByIsDeadlinedNotAndDueDateIsBefore("true", now);
        for (Task task : tasks) {
            task.setIsDeadlined("true");
            emailService.sendEmail(task.getUser().getEmail(), "Your task '"+task.getName()+"' is deadlined", "Please, check your tasks and complete them");
        }
        taskRepository.saveAll(tasks);
        log.info("Taken time={}", System.currentTimeMillis() - start);
    }
}

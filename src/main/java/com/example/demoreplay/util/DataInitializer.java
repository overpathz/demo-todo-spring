package com.example.demoreplay.util;

import com.example.demoreplay.entity.Role;
import com.example.demoreplay.entity.Task;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.repository.TaskRepository;
import com.example.demoreplay.repository.UserRepository;
import com.example.demoreplay.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        User user = User.builder()
                .username("overpathz111")
                .password("examplePassword111")
                .email("overpathz111@gmail.com")
                .role(Role.ROLE_ADMIN)
                .build();

        userRepository.save(user);

        for (int i = 0; i < 1_000_000; i++) {
            Task task = new Task();
            task.setUser(user);
            task.setDescription("some desc " + i);
            task.setName("some name " + i);
            task.setIsDeadlined("false");
            task.setCreatedAt(LocalDateTime.now());
            task.setDueDate(LocalDateTime.now().plusHours(2));
            taskRepository.save(task);
        }
    }
}

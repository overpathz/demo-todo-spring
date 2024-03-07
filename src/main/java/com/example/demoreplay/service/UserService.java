package com.example.demoreplay.service;

import com.example.demoreplay.entity.Role;
import com.example.demoreplay.entity.Task;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.repository.TaskRepository;
import com.example.demoreplay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public Task addUserTask(Long id, Task task) {
        User user = userRepository.findById(id).orElseThrow();
        user.addTask(task);
        userRepository.save(user);
        return user.getTasks().get(user.getTasks().size() - 1);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUserWithTasks(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        int size = user.getTasks().size();
        return user;
    }

    @Transactional
    public void removeTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow();
        Task task = taskRepository.findById(taskId).orElseThrow();
        if (!user.getTasks().contains(task)) {
           throw new RuntimeException("Не твоя таска Exception!");
        }
        user.removeTask(task);
        taskRepository.delete(task);
        log.info("Видалено");
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Користувач вже існує!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Користувач вже існує!");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Корстувача не знайдено"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}

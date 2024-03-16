package com.example.demoreplay.service;

import com.example.demoreplay.dto.Content;
import com.example.demoreplay.dto.UserResponse;
import com.example.demoreplay.entity.Role;
import com.example.demoreplay.entity.Task;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.exception.UserNotFoundException;
import com.example.demoreplay.repository.TaskRepository;
import com.example.demoreplay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public Task addUserEvent(Long id, Task task) {
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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
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

    public UserResponse getCustomResponse(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> all = userRepository.findAll(pageable);
        return UserResponse.builder().userList(all.getContent()).contentSize(all.getSize()).build();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}

package com.example.demoreplay.controller;

import com.example.demoreplay.dto.InformationDto;
import com.example.demoreplay.dto.TaskDto;
import com.example.demoreplay.entity.Task;
import com.example.demoreplay.mapper.TaskDtoMapper;
import com.example.demoreplay.service.UserService;
import com.example.demoreplay.util.SecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Security;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final TaskDtoMapper taskDtoMapper;

    @PostMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addNote(@PathVariable Long id, @RequestBody Task task, @AuthenticationPrincipal UserDetails principal) {
        Task task1 = userService.addUserTask(id, task);
        log.info("Created task id: {}. Name of task: {}", task1.getId(), task1.getName());
        return task1;
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public void deleteNote(@PathVariable Long userId, @PathVariable Long taskId) {
        userService.removeTask(userId, taskId);
    }

    @GetMapping("/{userId}/tasks")
    public List<TaskDto> getTasks(@PathVariable Long userId) {
        return userService.getUserWithTasks(userId).getTasks().stream()
                .map(taskDtoMapper::mapToDto)
                .toList();
    }
}

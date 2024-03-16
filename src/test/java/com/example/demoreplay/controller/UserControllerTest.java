package com.example.demoreplay.controller;

import com.example.demoreplay.entity.Role;
import com.example.demoreplay.entity.Task;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.mapper.TaskDtoMapper;
import com.example.demoreplay.service.UserService;
import com.example.demoreplay.service.auth.JwtService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private TaskDtoMapper taskDtoMapper;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void UserController_getUser_success() throws Exception {
        when(userService.findUserById(any())).thenReturn(getUser());

        mockMvc.perform(get("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(getUser())))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        MockHttpServletResponse response = result.getResponse();
                        Cookie testCookie = response.getCookie("testcookie");
                        if (testCookie == null) {
                            throw new AssertionError("null cookie");
                        }
                        response.getWriter().println("sdfdsfsdf");
                    }
                })
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("u1"));
    }

    @Test
    void addNote() throws Exception {
        // Mocked User and Task
        User user = new User(1L, "u1", "pass", "email", Role.ROLE_USER, Collections.emptyList(), Collections.emptyList());
        Task task = new Task(1L, "Name", "Description", LocalDateTime.now(), "false", LocalDateTime.now(), user);

        // Mocking userService.addUserTask method
        when(userService.addUserTask(any(Long.class), any(Task.class))).thenReturn(task);

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users/1/tasks")
                        .content(objectMapper.writeValueAsString(task))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.description").value("Description"));

    }

    private static User getUser() {
        return new User(1L, "u1", "pass", "email", Role.ROLE_USER, List.of(), List.of());
    }
}
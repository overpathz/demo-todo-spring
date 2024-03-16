package com.example.demoreplay.service;

import com.example.demoreplay.dto.UserResponse;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.repository.UserRepository;
import com.example.demoreplay.util.UserUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final static String USERNAME = "overpathz";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void UserService_findByUsername_success() {
        Optional<User> optionalUser = Optional.ofNullable(UserUtil.getUser(USERNAME));
        when(userRepository.findByUsername(USERNAME)).thenReturn(optionalUser);
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> userService.getByUsername(USERNAME));
    }

    @Test
    public void UserService_findByUsername_user_not_found() {
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findByUsername(USERNAME)).thenReturn(optionalUser);
        assertThatThrownBy(() -> userService.getByUsername(USERNAME));
    }

    @Test
    public void UserService_getCustomResponse_success() {
        List<User> userList = List.of(UserUtil.getUser("u1"), UserUtil.getUser("u2"));
        Page<User> page = new PageImpl<>(userList, mock(Pageable.class), 2);
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);
        UserResponse customResponse = userService.getCustomResponse(1, 2);
        assertThat(customResponse.getUserList()).hasSize(2);
    }
}

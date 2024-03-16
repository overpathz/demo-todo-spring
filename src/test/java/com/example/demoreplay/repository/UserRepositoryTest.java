package com.example.demoreplay.repository;

import com.example.demoreplay.entity.Role;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User defaultUser;

    @BeforeEach
    public void setUp() {
        defaultUser = User.builder()
                .username("u1")
                .password("p1")
                .role(Role.ROLE_USER)
                .email("u1@").build();
    }

    @Test
    public void UserRepository_saveUser_success() {
        // arrange
        Long createdUserId = 1L;

        assertNull(defaultUser.getId());

        userRepository.save(defaultUser);
        boolean present = userRepository.findById(createdUserId).isPresent();
        assertTrue(present);
    }

    /*
    // mocks -> all
     */

    @Test
    public void UserRepository_updateUser_success() {
        assertNull(defaultUser.getId());
        userRepository.save(defaultUser);

        String newEmail = "sfsdf1";
        defaultUser.setEmail(newEmail);

        Assertions.assertEquals(defaultUser.getEmail(), newEmail);
    }

    @Test
    public void UserRepository_getUsersWithAdminRole_success() {
        // arrange
        Long createdUserId = 1L;

        User user1 = User.builder()
                .username("u1")
                .password("p1")
                .role(Role.ROLE_USER)
                .email("u1@").build();

        User admin1 = User.builder()
                .username("a1")
                .password("p1")
                .role(Role.ROLE_ADMIN)
                .email("ad1@").build();

        User admin2 = User.builder()
                .username("ad2")
                .password("p1")
                .role(Role.ROLE_ADMIN)
                .email("ad2@").build();

        int expectedAdmins = 2;

        // action
        userRepository.saveAll(List.of(user1, admin1, admin2));

        int usersWithAdminRole = userRepository.getUsersWithAdminRole();
        System.out.println(usersWithAdminRole);

        // assert
        assertEquals(expectedAdmins, usersWithAdminRole);
    }
}

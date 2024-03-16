package com.example.demoreplay.util;

import com.example.demoreplay.entity.Role;
import com.example.demoreplay.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public static User getUser(String username) {
        return User.builder()
                .username(username)
                .password("p1")
                .role(Role.ROLE_ADMIN)
                .email(username + "@").build();
    }
}

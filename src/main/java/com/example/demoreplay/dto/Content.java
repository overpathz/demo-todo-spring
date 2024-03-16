package com.example.demoreplay.dto;

import com.example.demoreplay.entity.User;

import java.util.List;

public record Content(List<User> userList) {
}

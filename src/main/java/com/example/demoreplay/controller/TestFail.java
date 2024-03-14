package com.example.demoreplay.controller;

import com.example.demoreplay.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tests")
@Slf4j
public class TestFail {

    @GetMapping
    public String test(@AuthenticationPrincipal User user) {
        System.out.println(user);
        return "200";
    }
}

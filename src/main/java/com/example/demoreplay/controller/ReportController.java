package com.example.demoreplay.controller;

import com.example.demoreplay.dto.ReportDto;
import com.example.demoreplay.service.ReportService;
import com.example.demoreplay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private static final long LAST_MIN_COUNT_REPORT = 5;
    private final ReportService reportService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ReportDto getReport(@PathVariable Long id, @RequestParam(required = false) Long lastMin) {
        long reportLastMin = lastMin == null ? LAST_MIN_COUNT_REPORT : lastMin;
        return reportService.createReport(userService.getUserWithTasks(id), reportLastMin);
    }
}

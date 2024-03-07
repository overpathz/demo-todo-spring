package com.example.demoreplay.dto;

import java.time.LocalDateTime;

public record TaskDto(String name, String description, LocalDateTime createdAt) {
}

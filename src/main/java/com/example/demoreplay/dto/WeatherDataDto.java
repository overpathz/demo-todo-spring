package com.example.demoreplay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherDataDto {
    @JsonProperty("main")
    private MainData main;
}



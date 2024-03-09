package com.example.demoreplay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainData {
    @JsonProperty("temp")
    private double temp;
    
    @JsonProperty("temp_max")
    private double maxTemp;
}
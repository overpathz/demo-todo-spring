package com.example.demoreplay.controller;

import com.example.demoreplay.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public double getWeather(@RequestParam String city) {
       return weatherService.getWeather(city).getMain().getTemp();
    }
}

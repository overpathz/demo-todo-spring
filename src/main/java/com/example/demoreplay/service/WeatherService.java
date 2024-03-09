package com.example.demoreplay.service;

import com.example.demoreplay.dto.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;

    @Value("${openweather.api.url}")
    private String apiUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherDataDto getWeather(String city) {
        String readyApiUrl = apiUrl.formatted(city, apiKey);
        return restTemplate.getForObject(readyApiUrl, WeatherDataDto.class);
    }
}

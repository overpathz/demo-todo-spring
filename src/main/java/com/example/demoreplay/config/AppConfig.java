package com.example.demoreplay.config;

import com.example.demoreplay.service.bonus.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final Map<String, BonusService> bonusServiceMap;

    @Bean("bonusMap")
    public Map<String, BonusService> customServices() {
        Map<String, BonusService> map = new HashMap<>();
        for (BonusService value : bonusServiceMap.values()) {
            map.put(value.getUserRole(), value);
        }
        return map;
    }

    @Bean
    public RestTemplate restTemplate() {
       return new RestTemplate();
    }
}

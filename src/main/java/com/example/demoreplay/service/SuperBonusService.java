package com.example.demoreplay.service;

import org.springframework.stereotype.Component;

@Component
public class SuperBonusService implements BonusService {
    @Override
    public String getBonus() {
        return "5000 EUR";
    }

    @Override
    public String getUserRole() {
        return "ROLE_SUPER";
    }
}

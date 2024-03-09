package com.example.demoreplay.service;

import org.springframework.stereotype.Component;

@Component
public class VipBonusService implements BonusService {
    @Override
    public String getBonus() {
        return "500 USD";
    }

    @Override
    public String getUserRole() {
        return "ROLE_VIP";
    }
}

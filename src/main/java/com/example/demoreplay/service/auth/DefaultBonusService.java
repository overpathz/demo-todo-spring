package com.example.demoreplay.service.auth;

import com.example.demoreplay.service.bonus.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultBonusService implements BonusService {
    @Override
    public String getBonus() {
        return "10 USD";
    }

    @Override
    public String getUserRole() {
        return "ROLE_USER";
    }
}

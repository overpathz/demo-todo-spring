package com.example.demoreplay.controller;

import com.example.demoreplay.service.BonusService;
import com.example.demoreplay.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/bonuses")
@Slf4j
public class BonusController {
    private final Map<String, BonusService> bonusMap;

    public BonusController(@Qualifier("bonusMap") Map<String, BonusService> bonusMap) {
        this.bonusMap = bonusMap;
    }

    @GetMapping("/get")
    public String getBonus() {
        String currentRole = SecurityUtil.getCurrentRole(); // ROLE_USER / ROLE_VIP
        return bonusMap.get(currentRole).getBonus();
    }
}

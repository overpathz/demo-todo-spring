package com.example.demoreplay.controller;

import com.example.demoreplay.entity.Event;
import com.example.demoreplay.entity.User;
import com.example.demoreplay.repository.EventRepository;
import com.example.demoreplay.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
@Slf4j
public class EventController {
    private final UserService userService;
    private final EventRepository eventRepository;

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event reqEvent) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        log.info("Event={}", reqEvent);
        log.info("Principal={}", user);
        Event event = new Event();
        event.setTitle(reqEvent.getTitle());
        event.setTime(reqEvent.getTime());
        event.setUser(user);

        Event savedEvent = eventRepository.save(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Event> getEventsAfter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return eventRepository.findAllByTimeAfter(date);
    }
}

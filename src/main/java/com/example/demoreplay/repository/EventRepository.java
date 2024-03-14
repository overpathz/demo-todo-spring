package com.example.demoreplay.repository;

import com.example.demoreplay.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByTimeAfter(LocalDateTime date);
}

package com.buskspot.controller;

import com.buskspot.domain.Schedule;
import com.buskspot.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.createSchedule(schedule));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Schedule>> mySchedule(@RequestParam String email) {
        return ResponseEntity.ok(scheduleService.getSchedulesByEmail(email));
    }
}

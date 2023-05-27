package com.bosgii.internshipmanagement.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.services.DeadlineService;

@RestController
@RequestMapping("/api")
public class DeadlineController {
    private final DeadlineService deadlineService;

    public DeadlineController(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @PostMapping("/deadline")
    public void setInitialDeadline(@RequestBody Date deadline) {
        deadlineService.setInitialDeadline(deadline);
    }

    @PostMapping("/deadline/{internshipId}")
    public void extendDeadline(@PathVariable Long internshipId, @RequestBody Date deadline) {
        deadlineService.extendDeadline(internshipId, deadline);
    }
}

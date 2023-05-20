package com.bosgii.internshipmanagement.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.enums.MatchType;
import com.bosgii.internshipmanagement.services.MatchService;

@RestController
@RequestMapping("/api")
public class MatchController {
    private final MatchService matchService;
    
    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @PostMapping("/match")
    public Boolean matchInstuctorsWithInternships(@RequestParam MatchType matchType){
        return matchService.matchInstructorsWithInternships(matchType);
    }
}

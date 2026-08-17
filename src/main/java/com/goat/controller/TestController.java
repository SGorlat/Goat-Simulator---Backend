package com.goat.controller;

import com.goat.service.FootballDataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final FootballDataService footballDataService;

    public TestController(FootballDataService footballDataService) {
        this.footballDataService = footballDataService;
    }

    @GetMapping(value ="/teams/{code}", produces = "application/json")
    public String getTeams(@PathVariable String code) {
        return footballDataService.fetchTeamsByCompetition(code);
    }
}
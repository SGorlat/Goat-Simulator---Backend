package com.goat.controller;

import com.goat.model.Ranking;
import com.goat.service.RankingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    // GET /api/rankings → top 10 mejores carreras
    @GetMapping
    public List<Ranking> getTopRankings() {
        return rankingService.getTopRankings();
    }

    // POST /api/rankings/{id} → guardar carrera en el Hall of Fame al retirarse
    @PostMapping("/{id}")
    public Ranking saveToRanking(@PathVariable Long id) {
        return rankingService.saveToRanking(id);
    }

}

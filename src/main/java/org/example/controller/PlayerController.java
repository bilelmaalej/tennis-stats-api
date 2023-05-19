package org.example.controller;

import org.example.dto.TennisPlayersStatisticsDto;
import org.example.exception.DataNotMentionedException;
import org.example.exception.PlayerNotFoundException;
import org.example.model.Player;
import org.example.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public List<Player> getAllPlayersSortedByGamesWon(String jsonFile) {
        return playerService.getAllPlayersSortedByRank(jsonFile);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id, String jsonFile) throws PlayerNotFoundException {
        return playerService.getPlayerById(id, jsonFile);
    }

    @GetMapping("/statistics")
    public ResponseEntity<TennisPlayersStatisticsDto> getPlayerStatistics(String jsonFile) throws DataNotMentionedException {
        return ResponseEntity.ok().body(playerService.getPlayerStatistics(jsonFile));
    }
}

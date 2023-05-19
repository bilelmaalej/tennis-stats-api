package org.example.service;

import org.example.dto.TennisPlayersStatisticsDto;
import org.example.exception.DataNotMentionedException;
import org.example.exception.PlayerNotFoundException;
import org.example.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlayerService {
    List<Player> getAllPlayersSortedByRank(String jsonFile);
    Player getPlayerById(Long id, String jsonFile) throws PlayerNotFoundException;
    TennisPlayersStatisticsDto getPlayerStatistics(String jsonFile) throws DataNotMentionedException;

}

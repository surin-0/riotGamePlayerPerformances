package org.ajou.realcoding.riotGames.riotGamesPerformances.controller;

import org.ajou.realcoding.riotGames.riotGamesPerformances.domain.PlayerPerformance;
import org.ajou.realcoding.riotGames.riotGamesPerformances.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceController {

    @Autowired
    GameService gameService;

    @GetMapping("/lol/summoner/get-player-performance/by-name/{summonerName}")
    public PlayerPerformance getPlayerPerformance(@PathVariable String summonerName){
        String encryptedID = gameService.getEncryptedIdBySummonerName(summonerName).getId();
        return gameService.getPerformanceByEncryptedSummonerID(encryptedID);
    }
}

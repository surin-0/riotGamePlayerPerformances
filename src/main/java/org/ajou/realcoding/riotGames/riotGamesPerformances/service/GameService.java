package org.ajou.realcoding.riotGames.riotGamesPerformances.service;

import org.ajou.realcoding.riotGames.riotGamesPerformances.api.RiotGamesPerformancesApiClient;
import org.ajou.realcoding.riotGames.riotGamesPerformances.domain.PlayerEncryptedID;
import org.ajou.realcoding.riotGames.riotGamesPerformances.domain.PlayerPerformance;
import org.ajou.realcoding.riotGames.riotGamesPerformances.repository.CurrentPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    RiotGamesPerformancesApiClient riotGamesPerformancesApiClient;

    @Autowired
    CurrentPerformanceRepository currentPerformanceRepository;

    public PlayerEncryptedID getEncryptedIdBySummonerName(String summonerName) {
        return riotGamesPerformancesApiClient.requestEncryptedID(summonerName);
    }

    public PlayerPerformance getPerformanceByEncryptedSummonerID(String encryptedSummonerId) {
        currentPerformanceRepository.manageCurrentPerformance(riotGamesPerformancesApiClient.requestPerformance(encryptedSummonerId));
        return currentPerformanceRepository.findCurrentPerformance(encryptedSummonerId);
    }
}

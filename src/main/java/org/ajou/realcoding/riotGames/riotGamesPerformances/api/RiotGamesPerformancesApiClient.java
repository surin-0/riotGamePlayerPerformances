package org.ajou.realcoding.riotGames.riotGamesPerformances.api;

import org.ajou.realcoding.riotGames.riotGamesPerformances.domain.PlayerEncryptedID;
import org.ajou.realcoding.riotGames.riotGamesPerformances.domain.PlayerPerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotGamesPerformancesApiClient {
    private final String apikey = "RGAPI-ec1bf40e-0377-4c43-a4c4-92cc97032e15";
    private final String summonorV4Url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={apikey}";
    private final String leagueV4Url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={apikey}";

    @Autowired
    RestTemplate restTemplate;

    public PlayerEncryptedID requestEncryptedID(String summonerName) {
        return restTemplate.exchange(summonorV4Url, HttpMethod.GET, null, PlayerEncryptedID.class, summonerName, apikey).getBody();
    }

    public PlayerPerformance requestPerformance(String encryptedSummonerId) {
        PlayerPerformance[] performances = restTemplate.exchange(leagueV4Url, HttpMethod.GET, null, PlayerPerformance[].class, encryptedSummonerId, apikey).getBody();
        return performances[0];
    }
}
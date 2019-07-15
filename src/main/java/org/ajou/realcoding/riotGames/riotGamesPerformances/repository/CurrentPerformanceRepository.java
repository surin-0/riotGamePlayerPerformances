package org.ajou.realcoding.riotGames.riotGamesPerformances.repository;

import org.ajou.realcoding.riotGames.riotGamesPerformances.domain.PlayerPerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CurrentPerformanceRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void manageCurrentPerformance(PlayerPerformance playerPerformance){
        Query query = Query.query(Criteria.where("summonerId").is(playerPerformance.getSummonerId()));
        if(mongoTemplate.exists(query, PlayerPerformance.class)){
//            mongoTemplate.remove(query, PlayerPerformance.class);
//            mongoTemplate.insert(playerPerformance);
            Update update = new Update();
            update.set("losses", -1);
            mongoTemplate.updateMulti(query, update, "playerPerformance");
        }
        else {
            mongoTemplate.insert(playerPerformance);
        }
    }

    public PlayerPerformance findCurrentPerformance(String encryptedSummonerId) {
        Query query = Query.query(Criteria.where("summonerId").is(encryptedSummonerId));
        return mongoTemplate.findOne(query, PlayerPerformance.class);
    }
}

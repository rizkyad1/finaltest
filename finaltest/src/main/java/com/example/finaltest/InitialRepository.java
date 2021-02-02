package com.example.finaltest;

import com.example.finaltest.model.Initial;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InitialRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public InitialRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveInitial(Initial initial){
        hashOperations.put("INITIAL", initial.getUuid(), initial);
    }

    public List<Initial> getInitial(){
        return hashOperations.values("INITIAL");
    }

    public Initial getById(String id){
        return (Initial) hashOperations.get("INITIAL", id);
    }
}

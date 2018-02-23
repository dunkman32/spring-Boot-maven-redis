package com.example.redisspringdemo;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String,User> redisTemplate;
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void save(User user) {
        hashOperations.put("USER", user.getName(), user);
    }

    @Override
    public User findByName(String name) {
     return (User) hashOperations.get("USER",name);
    }

}

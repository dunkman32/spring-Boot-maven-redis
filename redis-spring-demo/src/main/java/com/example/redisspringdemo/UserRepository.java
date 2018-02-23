package com.example.redisspringdemo;

public interface UserRepository {
     void save(User user);
     User findByName(String name);
}

package com.example.chasky.features.user;

import java.util.List;

import lombok.Getter;

@Getter
public class UserService {
    List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    
}

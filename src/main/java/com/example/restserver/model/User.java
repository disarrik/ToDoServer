package com.example.restserver.model;

import com.example.restserver.entity.UserEntity;

public class User {
    private String fullname;
    private String email;


    private User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public static User entityToModel(UserEntity userEntity) {
        return new User(userEntity.getFullname(), userEntity.getEmail());
    }
}

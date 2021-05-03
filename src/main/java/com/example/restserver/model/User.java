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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

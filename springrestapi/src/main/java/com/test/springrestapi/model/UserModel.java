package com.test.springrestapi.model;

import com.test.springrestapi.entity.UserEntity;

public class UserModel {

    private Long id;

    private String name;

    public static UserModel toModel(UserEntity entity) {
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setName(entity.getName());
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

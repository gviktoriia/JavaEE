package com.example.demo.Entity;

import java.util.List;

public class UserEntityBuilder {
    private Integer id;
    private String login;
    private String password;
    private List<PermissionEntity> permissions;
    private List<BookEntity> favorites;

    UserEntityBuilder() {
    }

    public UserEntityBuilder id(final Integer id) {
        this.id = id;
        return this;
    }

    public UserEntityBuilder login(final String login) {
        this.login = login;
        return this;
    }

    public UserEntityBuilder password(final String password) {
        this.password = password;
        return this;
    }

    public UserEntityBuilder permissions(final List<PermissionEntity> permissions) {
        this.permissions = permissions;
        return this;
    }

    public UserEntityBuilder favorites(final List<BookEntity> favorites) {
        this.favorites = favorites;
        return this;
    }

    public UserEntity build() {
        return new UserEntity(this.id, this.login, this.password, this.permissions, this.favorites);
    }

    public String toString() {
        return "UserEntity.UserEntityBuilder(id=" + this.id + ", login=" + this.login + ", password=" + this.password + ", permissions=" + this.permissions + ", favorites=" + this.favorites + ")";
    }
}


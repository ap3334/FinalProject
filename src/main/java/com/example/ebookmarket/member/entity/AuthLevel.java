package com.example.ebookmarket.member.entity;

import lombok.Getter;

@Getter
public enum AuthLevel {

    USER("user", 3), AUTHOR("author", 4), ADMIN("admin", 7);

    private String name;
    private int level;

    AuthLevel(String name, int level) {
        this.name = name;
        this.level = level;
    }

}

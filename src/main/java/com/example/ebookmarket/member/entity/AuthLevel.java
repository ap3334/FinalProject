package com.example.ebookmarket.member.entity;

import lombok.Getter;

@Getter
public enum AuthLevel {

    USER(3), ADMIN(7);

    private int level;

    AuthLevel(int level) {
        this.level = level;
    }

}

package com.example.ebookmarket.member.entity;

import com.example.ebookmarket.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {

    private String username;

    private String password;

    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private AuthLevel authLevel;

    public Member(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}

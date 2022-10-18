package com.example.ebookmarket.security.dto;

import com.example.ebookmarket.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
public class MemberContext extends User {

    private Long id;

    private LocalDateTime createDate;

    @Setter
    private LocalDateTime updateDate;

    private String nickname;

    private String email;

    private String authLevel;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getId();
        this.createDate = member.getCreateDate();
        this.updateDate = member.getUpdateDate();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.authLevel = member.getAuthLevel().getName();
    }
}

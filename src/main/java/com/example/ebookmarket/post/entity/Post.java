package com.example.ebookmarket.post.entity;

import com.example.ebookmarket.base.entity.BaseEntity;
import com.example.ebookmarket.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Post extends BaseEntity {

    @ManyToOne
    private Member author;

    private String subject;

    private String content;

    private String contentHtml;

}

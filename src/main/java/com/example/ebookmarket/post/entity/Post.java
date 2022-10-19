package com.example.ebookmarket.post.entity;

import com.example.ebookmarket.base.entity.BaseEntity;
import com.example.ebookmarket.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
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

    @Lob
    private String content;

    @Lob
    private String contentHtml;

}

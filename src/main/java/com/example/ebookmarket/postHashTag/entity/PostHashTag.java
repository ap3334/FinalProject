package com.example.ebookmarket.postHashTag.entity;

import com.example.ebookmarket.base.entity.BaseEntity;
import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.postKeyword.entity.PostKeyword;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Table(indexes = @Index(name="unique_idx_post_keyword", columnList = "post_id, post_keyword_id", unique = true))
public class PostHashTag extends BaseEntity {

    @ManyToOne
    @ToString.Exclude
    private Member member;

    @ManyToOne
    @ToString.Exclude
    private Post post;

    @ManyToOne
    @ToString.Exclude
    private PostKeyword postKeyword;


}

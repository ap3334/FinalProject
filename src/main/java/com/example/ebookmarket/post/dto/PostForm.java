package com.example.ebookmarket.post.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class PostForm {

    private String subject;
    private String content;
    private String contentHtml;
    private String keywords;

}

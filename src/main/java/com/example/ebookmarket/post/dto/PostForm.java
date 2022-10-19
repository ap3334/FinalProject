package com.example.ebookmarket.post.dto;

import lombok.Data;

@Data
public class PostForm {

    private String subject;
    private String content;
    private String contentHtml;
    private String keywords;

}

package com.example.ebookmarket.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostDetailDto {

    private long id;
    private String subject;
    private String username;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String contentHtml;
    private String contentMarkdown;
    private String hashTagContents;

}

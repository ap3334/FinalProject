package com.example.ebookmarket.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostListDto {

    private long id;
    private String subject;
    private String username;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}

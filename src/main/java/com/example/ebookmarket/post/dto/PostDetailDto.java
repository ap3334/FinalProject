package com.example.ebookmarket.post.dto;

import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import com.example.ebookmarket.postKeyword.entity.PostKeyword;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class PostDetailDto {

    private long id;
    private String subject;
    private String username;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String content;
    private String hashTagContents;

}

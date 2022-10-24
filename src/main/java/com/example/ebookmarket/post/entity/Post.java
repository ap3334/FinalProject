package com.example.ebookmarket.post.entity;

import com.example.ebookmarket.base.entity.BaseEntity;
import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String getExtra_inputValue_hashTagContents() {
        Map<String, Object> extra = getExtra();

        if (extra.containsKey("postHashTags") == false) {
            return "";
        }

        List<PostHashTag> postHashTags = (List<PostHashTag>) extra.get("postHashTags");

        if (postHashTags.isEmpty()) {
            return "";
        }

        return postHashTags
                .stream()
                .map(hashTag -> "#" + hashTag.getPostKeyword().getContent())
                .sorted()
                .collect(Collectors.joining(" "));
    }

}

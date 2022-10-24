package com.example.ebookmarket.postHashTag.service;

import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import com.example.ebookmarket.postHashTag.repository.PostHashTagRepository;
import com.example.ebookmarket.postKeyword.entity.PostKeyword;
import com.example.ebookmarket.postKeyword.service.PostKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostHashTagService {

    private final PostKeywordService postKeywordService;
    private final PostHashTagRepository postHashTagRepository;

    public List<PostHashTag> getHashTags(Post post) {

        return postHashTagRepository.findAllByPostId(post.getId());

    }

    public void applyHashTags(Post post, String keywords) {

        List<PostHashTag> oldHashTags = getHashTags(post);

        List<String> keywordContents = Arrays.stream(keywords.split("#"))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        List<PostHashTag> needToDelete = new ArrayList<>();

        for (PostHashTag oldHashTag : oldHashTags) {
            boolean contains = keywordContents.stream().anyMatch(s -> s.equals(oldHashTag.getPostKeyword().getContent()));

            if (contains == false) {
                needToDelete.add(oldHashTag);
            }
        }

        needToDelete.forEach(hashTag -> {
            postHashTagRepository.delete(hashTag);
        });


        keywordContents.forEach(keywordContent -> {
            saveHashTag(post, keywordContent);
        });


    }

    private PostHashTag saveHashTag(Post post, String keywordContent) {
        PostKeyword keyword = postKeywordService.save(keywordContent);

        Optional<PostHashTag> opHashTag = postHashTagRepository.findByPostIdAndPostKeywordId(post.getId(), keyword.getId());

        if (opHashTag.isPresent()) {
            return opHashTag.get();
        }

        PostHashTag hashTag = PostHashTag.builder()
                .post(post)
                .postKeyword(keyword)
                .build();

        postHashTagRepository.save(hashTag);

        return hashTag;
    }
}

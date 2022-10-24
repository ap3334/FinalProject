package com.example.ebookmarket.postHashTag.service;

import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import com.example.ebookmarket.postHashTag.repository.PostHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostHashTagService {

    private final PostHashTagRepository postHashTagRepository;

    public List<PostHashTag> getHashTags(Post post) {

        return postHashTagRepository.findAllByPostId(post.getId());

    }
}

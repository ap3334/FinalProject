package com.example.ebookmarket.post.service;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.post.dto.PostForm;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.entity.PostHashTag;
import com.example.ebookmarket.post.entity.PostKeyword;
import com.example.ebookmarket.post.repository.PostHashTagRepository;
import com.example.ebookmarket.post.repository.PostKeywordRepository;
import com.example.ebookmarket.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostKeywordRepository postKeywordRepository;
    private final PostHashTagRepository postHashTagRepository;

    public List<Post> getAllPost() {

        List<Post> posts = postRepository.findAll();

        return posts;
    }

    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElse(new Post());

        return post;
    }

    public Post writePost(PostForm postForm, Member member) {

        Post post = Post.builder()
                .author(member)
                .subject(postForm.getSubject())
                .content(postForm.getContent())
                .contentHtml(postForm.getContentHtml())
                .build();

        postRepository.save(post);

        String keywords[] = postForm.getKeywords().split("#");

        for (int i = 1; i < keywords.length; i++) {
            PostKeyword postKeywords = PostKeyword.builder()
                    .content(keywords[i].trim())
                    .build();

            postKeywordRepository.save(postKeywords);

            PostHashTag postHashTag = PostHashTag.builder()
                    .member(member)
                    .post(post)
                    .postKeyword(postKeywords)
                    .build();

            postHashTagRepository.save(postHashTag);

        }

        return post;


    }

    public List<Post> getLatestPost(int cnt) {

        Pageable pageable = PageRequest.of(0, cnt, Sort.by("createDate"));

        return postRepository.findAll(pageable).stream().toList();

    }
}

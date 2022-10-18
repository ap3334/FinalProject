package com.example.ebookmarket.post.service;

import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPost() {

        List<Post> posts = postRepository.findAll();

        return posts;
    }
}

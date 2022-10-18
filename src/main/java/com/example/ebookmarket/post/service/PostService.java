package com.example.ebookmarket.post.service;

import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPost() {

        List<Post> posts = postRepository.findAll();

        return posts;
    }

    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElse(new Post());

        return post;
    }
}

package com.example.ebookmarket.post.controller;

import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public List<Post> getPost() {

        List<Post> posts = postService.getAllPost();

        return posts;
    }

}

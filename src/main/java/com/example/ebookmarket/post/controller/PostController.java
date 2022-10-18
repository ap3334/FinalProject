package com.example.ebookmarket.post.controller;

import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String getPostList(Model model) {

        List<Post> posts = postService.getAllPost();

        model.addAttribute("posts", posts);

        return "post/list";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Post post = postService.getPost(id);

        model.addAttribute("post", post);

        return "post/detail";
    }



}

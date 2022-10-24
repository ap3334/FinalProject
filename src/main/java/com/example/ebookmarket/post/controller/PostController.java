package com.example.ebookmarket.post.controller;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.service.MemberService;
import com.example.ebookmarket.post.dto.PostForm;
import com.example.ebookmarket.post.dto.PostListDto;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.service.PostService;
import com.example.ebookmarket.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final MemberService memberService;

    @GetMapping("/list")
    public String getPostList(Model model) {

        List<PostListDto> posts = postService.getAllPost();

        model.addAttribute("posts", posts);

        return "post/list";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Post post = postService.getPost(id);

        model.addAttribute("post", post);

        return "post/detail";
    }

    @GetMapping("/write")
    public String writeForm() {

        return "post/write";
    }

    @PostMapping("/write")
    public ResponseEntity write(@AuthenticationPrincipal MemberContext memberContext, @RequestBody PostForm post, RedirectAttributes redirectAttributes) {

        Member member = memberService.findByUsername(memberContext.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Post savedPost = postService.writePost(post, member);

        redirectAttributes.addAttribute("post", savedPost);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/modify")
    public String modifyPostForm(@PathVariable Long id, Model model) {

        Post post = postService.getPost(id);

        model.addAttribute("post", post);

        return "post/modify";
    }



}

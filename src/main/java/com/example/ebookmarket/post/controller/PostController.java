package com.example.ebookmarket.post.controller;

import com.example.ebookmarket.member.service.MemberService;
import com.example.ebookmarket.post.dto.PostDetailDto;
import com.example.ebookmarket.post.dto.PostFormDto;
import com.example.ebookmarket.post.dto.PostListDto;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.service.PostService;
import com.example.ebookmarket.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
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

        PostDetailDto post = postService.getPostDetailDto(id);

        model.addAttribute("post", post);

        return "post/detail";
    }

    @GetMapping("/write")
    public String write() {

        return "post/write";
    }

    @PostMapping("/write")
    public ResponseEntity write(@AuthenticationPrincipal MemberContext memberContext, @RequestBody PostFormDto post, RedirectAttributes redirectAttributes) {

        Post savedPost = postService.writePost(post, memberContext.getId());

        redirectAttributes.addAttribute("post", savedPost);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/modify")
    public String modify(@PathVariable Long id, Model model) {

        PostDetailDto post = postService.getPostDetailDto(id);

        model.addAttribute("post", post);

        return "post/modify";
    }

    @PostMapping("/{id}/modify")
    public ResponseEntity modify(@AuthenticationPrincipal MemberContext memberContext,
                                 @PathVariable Long id,
                                 @RequestBody PostFormDto postFormDto) {

        postService.modify(id, postFormDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal MemberContext memberContext) {

        PostDetailDto post = postService.getPostDetailDto(id);

        if (!post.getUsername().equals(memberContext.getUsername())) {
            throw new IllegalArgumentException("접근 권한이 없습니다.");
        }

        postService.delete(id);

        return "redirect:/";
    }



}

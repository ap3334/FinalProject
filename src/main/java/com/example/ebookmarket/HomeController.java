package com.example.ebookmarket;

import com.example.ebookmarket.member.service.MemberService;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {

        List<Post> posts = postService.getLatestPost(100);

        model.addAttribute("posts", posts);

        return "home";

    }
}

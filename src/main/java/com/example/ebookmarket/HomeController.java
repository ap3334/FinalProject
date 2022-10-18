package com.example.ebookmarket;

import com.example.ebookmarket.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;



    @GetMapping("/")
    public String home() {

        return "home";

    }
}

package com.example.ebookmarket.member.controller;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.service.MemberService;
import com.example.ebookmarket.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {

        return "join";
    }

    @PostMapping("/join")
    public Member join(Member member) {

        Member joinMember = memberService.join(member);

        return joinMember;
    }


    @GetMapping("/login")
    public String loginForm() {

        return "login";
    }



}

package com.example.ebookmarket.member.controller;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.service.MemberService;
import com.example.ebookmarket.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        Member joinMember = memberService.save(member);

        return joinMember;
    }


    @GetMapping("/login")
    public String loginForm() {

        return "login";
    }

    @GetMapping("/modify")
    public String modifyForm() {

        return "modify";
    }

    @PostMapping("/modify")
    public Member modify(@AuthenticationPrincipal MemberContext memberContext, String email, String nickname) {

        Member member = memberService.findByUsername(memberContext.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        member.setEmail(email);
        member.setNickname(nickname);

        memberService.save(member);

        return member;
    }


}

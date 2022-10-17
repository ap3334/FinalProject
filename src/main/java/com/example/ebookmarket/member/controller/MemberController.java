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

        Member joinMember = memberService.join(member);

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

        memberService.modify(member, email, nickname);

        return member;
    }

    @GetMapping("/modifyPassword")
    public String modifyPasswordForm() {

        return "modify";
    }

    @PostMapping("/modifyPassword")
    public Member modifyPassword(@AuthenticationPrincipal MemberContext memberContext, String oldPassword, String password, String passwordConfirm) {

        Member member = memberService.findByUsername(memberContext.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (!memberService.checkPassword(member, oldPassword)) {
            return member;
        }

        if (password != passwordConfirm) {
            return member;
        }

        Member modifiedMember = memberService.modifyPassword(member, password);

        return modifiedMember;
    }



}

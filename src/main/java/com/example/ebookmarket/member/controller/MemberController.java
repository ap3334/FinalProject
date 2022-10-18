package com.example.ebookmarket.member.controller;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.service.MemberService;
import com.example.ebookmarket.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {

        return "member/join";
    }

    @PostMapping("/join")
    public String join(Member member) {

        memberService.join(member);

        return "redirect:/member/login";
    }

    // 아이디 중복 확인 메서드
    @PostMapping("/checkId")
    public ResponseEntity checkId(@RequestBody Member member) {

        boolean isOverlap = memberService.checkUsernameOverlap(member.getUsername());

        if (isOverlap) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);

    }


    @GetMapping("/login")
    public String loginForm() {

        return "member/login";
    }

    @GetMapping("/mypage")
    public String myPage() {

        return "member/mypage";
    }

    @GetMapping("/modify")
    public String modifyForm() {

        return "member/modify";
    }

    @PostMapping("/modify")
    public String modify(@AuthenticationPrincipal MemberContext memberContext, String email, String nickname) {

        System.out.println(email);
        System.out.println(nickname);

        Member member = memberService.findByUsername(memberContext.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        memberService.modify(member, email, nickname);

        memberContext.setUpdateDate(member.getUpdateDate());
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberContext, member.getPassword(), memberContext.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/member/mypage";
    }

    @GetMapping("/modifyPassword")
    public String modifyPasswordForm() {

        return "member/modifyPassword";
    }

    @PostMapping("/modifyPassword")
    public String modifyPassword(@AuthenticationPrincipal MemberContext memberContext, String oldPassword, String password, String passwordConfirm) {

        Member member = memberService.findByUsername(memberContext.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Member modifiedMember = memberService.modifyPassword(member, oldPassword, password, passwordConfirm);

        return "redirect:/member/mypage";

    }

    @GetMapping("/findUsername")
    public String findUsernameForm() {

        return "findUsernameForm";
    }

    @PostMapping("/findUsername")
    public String findUsername(String email) {

        String username = memberService.findUsername(email);

        return username;
    }

    @GetMapping("/findPassword")
    public String findPasswordForm() {

        return "findPasswordForm";
    }

    @PostMapping("/findPassword")
    public String findPassword(String username, String email) {

        String tempPassword = memberService.findPassword(username, email);

        return tempPassword;
    }


}

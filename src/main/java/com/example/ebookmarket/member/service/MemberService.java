package com.example.ebookmarket.member.service;

import com.example.ebookmarket.member.entity.AuthLevel;
import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder encoder;

    private final MemberRepository memberRepository;

    public Member join(Member member) {

        member.setAuthLevel(AuthLevel.USER);

        String rawPassword = member.getPassword();
        String encodePassword = encoder.encode(rawPassword);

        member.setPassword(encodePassword);

        memberRepository.save(member);

        return member;

    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member modify(Member member, String email, String nickname) {

        member.setEmail(email);
        member.setNickname(nickname);

        memberRepository.save(member);

        return member;

    }

    public boolean checkPassword(Member member, String password) {

        String encodePassword = encoder.encode(password);

        if (member.getPassword() == encodePassword) {
            return true;
        }
        else {
            return false;
        }

    }

    public Member modifyPassword(Member member, String oldPassword, String password, String passwordConfirm) {


        if (!checkPassword(member, oldPassword)) {
            return member;
        }

        if (password != passwordConfirm) {
            return member;
        }

        String encodePassword = encoder.encode(password);

        member.setPassword(encodePassword);

        memberRepository.save(member);

        return member;
    }

    public String findUsername(String email) {

        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member != null) {
            return member.getUsername();
        } else {
            return null;
        }

    }
}

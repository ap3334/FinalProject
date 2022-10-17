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

    public Member save(Member member) {

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
}

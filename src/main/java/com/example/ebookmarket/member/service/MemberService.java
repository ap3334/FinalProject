package com.example.ebookmarket.member.service;

import com.example.ebookmarket.member.entity.AuthLevel;
import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}

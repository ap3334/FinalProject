package com.example.ebookmarket.base;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevInitData {

    @Bean
    public CommandLineRunner initData(MemberService memberService) {
        return args -> {

            String password = "1234";
//            Member member1 = memberService.join(new Member("user1", password, "chwil2989@gmail.com", "user1nickname"));
//            Member member2 = memberService.join.html(new Member("user2", password, "chwil2989@gmail.com", "user2nickname"));
//            Member member3 = memberService.join.html(new Member("user3", password, "chwil2989@gmail.com", "user3nickname"));
//            Member member4 = memberService.join.html(new Member("user4", password, "chwil2989@gmail.com", "user4nickname"));

        };
    }

}

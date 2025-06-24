package com.example.oauth_back.member.service;

import com.example.oauth_back.member.controller.dto.LoginRequestDto;
import com.example.oauth_back.member.domain.Member;
import com.example.oauth_back.member.controller.dto.createRequestDto;
import com.example.oauth_back.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(createRequestDto request) {

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return memberRepository.save(member);
    }

    public Member login(LoginRequestDto request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("email 이 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("password가 일치하지 않습니다.");
        }
        return member;
    }
}

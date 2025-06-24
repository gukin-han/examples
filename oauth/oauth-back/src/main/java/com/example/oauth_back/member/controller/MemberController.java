package com.example.oauth_back.member.controller;

import com.example.oauth_back.common.auth.JwtTokenProvider;
import com.example.oauth_back.member.controller.dto.LoginRequestDto;
import com.example.oauth_back.member.controller.dto.LoginResponseDto;
import com.example.oauth_back.member.domain.Member;
import com.example.oauth_back.member.controller.dto.createRequestDto;
import com.example.oauth_back.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody createRequestDto request) {
        Member member = memberService.create(request);
        URI location = URI.create("/members/" + member.getId());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        // email, password 일치 검증
        Member member = memberService.login(request);

        // 일치할 경우 jwt access token 생성
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());

        // 응답 객체 생성
        LoginResponseDto response = LoginResponseDto.builder()
                .id(member.getId())
                .jwtToken(jwtToken)
                .build();

        return ResponseEntity.ok(response);
    }
}

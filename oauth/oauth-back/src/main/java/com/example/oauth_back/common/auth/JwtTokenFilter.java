package com.example.oauth_back.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

// 실무에서는 OncePerRequestFilter 를 사용 -> 서블릿 중복 요청을 방어 하기 위함
public class JwtTokenFilter extends OncePerRequestFilter {
    // 토큰을 검증하는 부분이 이 프로젝트에서 필요 없음
    private final String secretKey;
    private final Key SECRET_KEY; // 어떤 암호화를 쓸건지 설정된 시크릿


    public JwtTokenFilter(String secretKey) {
        this.secretKey = secretKey;
        this.SECRET_KEY = new SecretKeySpec(
                java.util.Base64.getDecoder().decode(secretKey),
                SignatureAlgorithm.HS512.getJcaName());
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = parseJwtToken(request);

        try {
            Claims claims = Jwts.parser()
                    .decryptWith((SecretKey) SECRET_KEY)
                    .build()
                    .parseEncryptedClaims(jwtToken)
                    .getPayload();

            // Authentication 객체 생성
            List<GrantedAuthority> authorities = new ArrayList<>();

            // ROLE_ 을 앞에 붙이는것이 관례이며, 스프링 권한 체크 어노테이션에서 요구되는 경우가 있다
            authorities.add(new SimpleGrantedAuthority("ROLE_" + claims.get("role")));
            UserDetails userDetails = new User(claims.getSubject(), "", authorities);

            // 일반적으로 패스워드는 사용처가 없다
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 사용자가 인증을 하면 Authentication 이 context 내부에 담겨
            // 요청 스코프 내에서 활용할 수 있다

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("invalid token");
        }
    }

    private String parseJwtToken(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        // 1. 헤더가 존재하고
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new AuthenticationServiceException("Authorization header is missing.");
        }

        // 2. Bearer로 시작하는지 (공백 포함 주의)
        String bearerPrefix = "Bearer ";
        if (!authorizationHeader.startsWith(bearerPrefix)) {
            throw new AuthenticationServiceException("Authorization header must start with 'Bearer '.");
        }

        // 3. 실제 토큰 추출
        String token = authorizationHeader.substring(bearerPrefix.length());

        // 4. 토큰 유효성 검사 (여기서 null, 빈 문자열, 만료, 형식 등을 검증)
        if (token.isBlank()) {
            throw new AuthenticationServiceException("Token is missing after 'Bearer '.");
        }

        return token.substring(7);
    }
}

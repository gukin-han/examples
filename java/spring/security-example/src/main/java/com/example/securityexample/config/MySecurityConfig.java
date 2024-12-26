package com.example.securityexample.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain mySecurityFilterchain(HttpSecurity http) throws Exception {

        // CSRF 비활성화 (테스트 목적)
        http.csrf(AbstractHttpConfigurer::disable);

        // HTTP Basic 인증
        http.httpBasic(httpBasic -> {});

        // 기본 로그인 폼
        http.formLogin(formLogin -> {});

        // 모든 요청 인증 필요
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // 커스텀 필터
        http.addFilterAfter(new TenantFilter(), AnonymousAuthenticationFilter.class);

        return http.build();
    }

    static class TenantFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
                throws IOException, ServletException {

            // 항상 HTTP 환경에서만 동작한다면 instanceof를 사용할 필요 없다.
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            // `X-` 접두사는 사용자 정의 헤더를 나타내기 위한 관행
            String tenantId = request.getHeader("X-Tenant-Id");
            if (tenantId == null || !tenantId.equals("valid-tenant")) {
                throw new AccessDeniedException("Access denied due to invalid tenant.");
            }
            chain.doFilter(request, response);
        }
    }
}

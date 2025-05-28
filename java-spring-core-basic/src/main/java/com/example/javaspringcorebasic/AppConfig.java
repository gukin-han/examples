package com.example.javaspringcorebasic;

import com.example.javaspringcorebasic.discount.DiscountPolicy;
import com.example.javaspringcorebasic.discount.RateDiscountPolicy;
import com.example.javaspringcorebasic.member.MemberService;
import com.example.javaspringcorebasic.member.MemberServiceImpl;
import com.example.javaspringcorebasic.member.MemoryMemberRepository;
import com.example.javaspringcorebasic.order.OrderService;
import com.example.javaspringcorebasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 어플리케이션의 설정 정보를 담당
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); // 구현체가 바뀔때 변경하는 부분
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); // 구현체가 바뀔때 변경하는 부분
    }
}

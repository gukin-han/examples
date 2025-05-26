package com.example.javaspringcorebasic;

import com.example.javaspringcorebasic.discount.DiscountPolicy;
import com.example.javaspringcorebasic.discount.FixDiscountPolicy;
import com.example.javaspringcorebasic.member.MemberService;
import com.example.javaspringcorebasic.member.MemberServiceImpl;
import com.example.javaspringcorebasic.member.MemoryMemberRepository;
import com.example.javaspringcorebasic.order.OrderService;
import com.example.javaspringcorebasic.order.OrderServiceImpl;

public class AppConfig {

    // 구현체를 결정한다
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 구현체가 바뀔때 변경하는 부분
    }

    // 문제점?
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy(); // 구현체가 바뀔때 변경하는 부분
    }
}

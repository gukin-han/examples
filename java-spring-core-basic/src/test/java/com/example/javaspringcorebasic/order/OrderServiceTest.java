package com.example.javaspringcorebasic.order;

import com.example.javaspringcorebasic.AppConfig;
import com.example.javaspringcorebasic.discount.DiscountPolicy;
import com.example.javaspringcorebasic.discount.RateDiscountPolicy;
import com.example.javaspringcorebasic.member.Grade;
import com.example.javaspringcorebasic.member.Member;
import com.example.javaspringcorebasic.member.MemberService;
import com.example.javaspringcorebasic.member.MemberServiceImpl;
import com.example.javaspringcorebasic.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

//    MemberService memberService;
//    OrderService orderService;
//
//    @BeforeEach
//    public void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
//    }

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());

        memberRepository.save(new Member(1L, "name", Grade.BASIC));
        orderService.createOrder(1L, "itemA", 10000);
    }

}
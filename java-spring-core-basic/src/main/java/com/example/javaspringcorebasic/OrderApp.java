package com.example.javaspringcorebasic;

import com.example.javaspringcorebasic.member.Grade;
import com.example.javaspringcorebasic.member.Member;
import com.example.javaspringcorebasic.member.MemberService;
import com.example.javaspringcorebasic.order.Order;
import com.example.javaspringcorebasic.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        long memberId = 1L;
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}

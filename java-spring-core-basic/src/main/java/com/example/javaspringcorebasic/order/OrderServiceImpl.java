package com.example.javaspringcorebasic.order;

import com.example.javaspringcorebasic.discount.DiscountPolicy;
import com.example.javaspringcorebasic.discount.FixDiscountPolicy;
import com.example.javaspringcorebasic.member.Member;
import com.example.javaspringcorebasic.member.MemberRepository;
import com.example.javaspringcorebasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

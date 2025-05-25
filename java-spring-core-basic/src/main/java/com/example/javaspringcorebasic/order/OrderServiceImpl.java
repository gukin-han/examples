package com.example.javaspringcorebasic.order;

import com.example.javaspringcorebasic.discount.DiscountPolicy;
import com.example.javaspringcorebasic.member.Member;
import com.example.javaspringcorebasic.member.MemberRepository;
import com.example.javaspringcorebasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // DIP 위반 : 인터페이스와 구체 클래스를 모두 의존성을 갖고 있다 -> 인터페이스만 의존해야한다
    // OCP 위반 : Fix -> Rate 변경은 클라이언트 소스를 함께 변경해야함
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

package com.example.javaspringcorebasic.discount;

import com.example.javaspringcorebasic.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}

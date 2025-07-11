package com.example.javaspringcorebasic.autowired;

import com.example.javaspringcorebasic.AutoAppConfig;
import com.example.javaspringcorebasic.discount.DiscountPolicy;
import com.example.javaspringcorebasic.member.Grade;
import com.example.javaspringcorebasic.member.Member;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = ac.getBean(DiscountService.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
    int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

    Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
    Assertions.assertThat(fixDiscountPrice).isEqualTo(1000);
    Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policies;

    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
      this.policyMap = policyMap;
      this.policies = policies;
      System.out.println("policyMap = " + policyMap); // 구현체 이름으로 키가 설정된다
      System.out.println("policies = " + policies);
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }
}

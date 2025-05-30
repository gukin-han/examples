package com.example.javaspringcorebasic.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  @Test
  void statefulServiceSingleton() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        TestConfig.class);
    StatefulService service1 = ac.getBean(StatefulService.class);
    StatefulService service2 = ac.getBean(StatefulService.class);

    // thread a : a사용자 10000원 주문
    service1.order("userA", 10000);

    // thread a : b사용자 20000원 주문
    service2.order("userB", 20000);

    // thraed a : a사용자 주문 금액 조회
    int price = service1.getPrice();

    Assertions.assertThat(price).isEqualTo(20000);

  }

  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }

}
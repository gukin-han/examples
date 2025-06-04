package com.example.javaspringcorebasic.autowired;

import com.example.javaspringcorebasic.AutoAppConfig;
import com.example.javaspringcorebasic.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

  @Test
  void autowiredOptionTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
  }

  static class TestBean {

    @Autowired(required = false)
    public void setNoBean1(Member noBean1) {
      System.out.println("noBean1 = " + noBean1); // 호출 자체가 안된다
    }

    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("noBean2 = " + noBean2); // 호출은 되지만 null로 입력됨
    }

    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("noBean3 = " + noBean3); // 호출되며 Optional로 전달됨
    }
  }

}

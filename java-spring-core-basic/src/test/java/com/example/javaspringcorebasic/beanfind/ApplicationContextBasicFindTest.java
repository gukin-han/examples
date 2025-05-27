package com.example.javaspringcorebasic.beanfind;

import static org.assertj.core.api.Assertions.*;

import com.example.javaspringcorebasic.AppConfig;
import com.example.javaspringcorebasic.member.MemberService;
import com.example.javaspringcorebasic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 이름없이 타입으로 조회")
  void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class); // 인터페이스로 조회
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
    // 구체타입으로 조회하면 변경시 유연성이 떨어진다
  void findBeanByName2() {
    MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); // 구체 타입
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 이름으로 조회X")
  void findBeanByNameX() {
    assertThatThrownBy(() -> ac.getBean("xxxxx", MemberService.class))
        .isInstanceOf(NoSuchBeanDefinitionException.class);
  }
}

package com.example.javaspringcorebasic.singleton;

import com.example.javaspringcorebasic.AppConfig;
import com.example.javaspringcorebasic.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService(); // 새 객체 생성
        MemberService memberService2 = appConfig.memberService(); // 새 객체 생성

        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }
}

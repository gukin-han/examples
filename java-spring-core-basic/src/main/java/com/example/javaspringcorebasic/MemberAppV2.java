package com.example.javaspringcorebasic;

import com.example.javaspringcorebasic.member.Grade;
import com.example.javaspringcorebasic.member.Member;
import com.example.javaspringcorebasic.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberAppV2 {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // appConfig 가 객체 생성 역할을 담당
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

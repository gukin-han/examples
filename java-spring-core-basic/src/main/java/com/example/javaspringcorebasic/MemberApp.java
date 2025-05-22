package com.example.javaspringcorebasic;

import com.example.javaspringcorebasic.member.Grade;
import com.example.javaspringcorebasic.member.Member;
import com.example.javaspringcorebasic.member.MemberService;
import com.example.javaspringcorebasic.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

package com.example.javaspringcorebasic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 더 이상 MemoryMemberRepository 에 의존하지 않는다
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용도

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

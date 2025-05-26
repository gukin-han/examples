package com.example.javaspringcorebasic.member;

public class MemberServiceImpl implements MemberService{

    // 더 이상 MemoryMemberRepository 에 의존하지 않는다
    private final MemberRepository memberRepository;

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
}

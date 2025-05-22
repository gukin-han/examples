package com.example.javaspringcorebasic.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}

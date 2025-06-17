package com.example.oauth_back.member.repository;

import com.example.oauth_back.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

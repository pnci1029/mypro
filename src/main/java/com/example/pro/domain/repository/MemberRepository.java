package com.example.pro.domain.repository;

import com.example.pro.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByName(String name);
}

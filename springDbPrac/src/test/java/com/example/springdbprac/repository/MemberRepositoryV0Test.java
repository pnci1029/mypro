package com.example.springdbprac.repository;

import com.example.springdbprac.inflearn.domain.Member;
import com.example.springdbprac.inflearn.repository.MemberRepositoryV0;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV0Test {

    MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV0", 10000);
        memberRepositoryV0.save(member);

        // findById
        Member memberResult = memberRepositoryV0.findById(member.getMemberId());
        assertThat(memberResult.getMemberId()).isEqualTo(member.getMemberId());

        // update
        memberRepositoryV0.update(member.getMemberId(), 20000);
        Member updateMember = memberRepositoryV0.findById(member.getMemberId());
        assertThat(updateMember).isNotEqualTo(member);

        // delete
        memberRepositoryV0.delete(member.getMemberId());
        assertThatThrownBy(() -> memberRepositoryV0.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}
package com.example.springdbprac.service;

import com.example.springdbprac.inflearn.domain.Member;
import com.example.springdbprac.inflearn.repository.MemberRepositoryV1;
import com.example.springdbprac.inflearn.service.MemberServiceV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static com.example.springdbprac.inflearn.connection.ConnectionConst.*;

class MemberServiceV1Test {
    public static String memberA = "memberA";
    public static String memberB = "memberB";

    private MemberRepositoryV1 memberRepository;
    private MemberServiceV1 memberService;

    @BeforeEach
    void beforeEach() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, NAME, PASSWORD);
        memberRepository = new MemberRepositoryV1(dataSource);
        memberService = new MemberServiceV1(memberRepository);
    }

    @Test
    @DisplayName("정상 계좌이체")
    void accountTransferOk() throws SQLException {
        //given
        Member memberA = new Member(MemberServiceV1Test.memberA, 10000);
        Member memberB = new Member(MemberServiceV1Test.memberB, 15000);

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        //when
        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 5000);

        //then
        Assertions.assertThat(memberA.getMoney()).isEqualTo(5000);
    }
}
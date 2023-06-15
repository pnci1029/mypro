package com.example.springdbprac.repository;

import com.example.springdbprac.connection.ConnectionConst;
import com.example.springdbprac.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static com.example.springdbprac.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV1Test {

    MemberRepositoryV1 memberRepositoryV1;

    @BeforeEach
    void BeforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, NAME, PASSWORD);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(NAME);
        dataSource.setPassword(PASSWORD);

        dataSource.setMaximumPoolSize(10);


        memberRepositoryV1 = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV0", 10000);
        memberRepositoryV1.save(member);

        // findById
        Member memberResult = memberRepositoryV1.findById(member.getMemberId());
        assertThat(memberResult.getMemberId()).isEqualTo(member.getMemberId());

        // update
        memberRepositoryV1.update(member.getMemberId(), 20000);
        Member updateMember = memberRepositoryV1.findById(member.getMemberId());
        assertThat(updateMember).isNotEqualTo(member);

        // delete
        memberRepositoryV1.delete(member.getMemberId());
        assertThatThrownBy(() -> memberRepositoryV1.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}
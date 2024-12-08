package hello.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @DisplayName("db 생성 및 조회 테스트")
    @Test
    void memberTest() {
        // given
        Member member = new Member("idA", "memberA");
        memberRepository.initTable();
        memberRepository.save(member);

        // when
        Member resultMember = memberRepository.find(member.getMemberId());

        // then
        assertThat(resultMember.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(resultMember.getName()).isEqualTo(member.getName());
    }
}
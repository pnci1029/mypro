package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.domain.search.MemberSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class MemberService {
    private final MemberSearchRepository memberSearchRepository;
    public List<Member> findByUserName(String userName) {
//        return memberSearchRepository.findMemberByName(userName);
        return memberSearchRepository.searchByMemberName(userName);
    }

    public void save(Member member) {
        Member savedMember = memberSearchRepository.save(member);
        log.info("id : {}",savedMember.getId());
        log.info("name : {}",savedMember.getName());
    }
}

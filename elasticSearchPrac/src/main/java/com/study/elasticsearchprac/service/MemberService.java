package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.domain.search.MemberSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service @RequiredArgsConstructor @Slf4j
public class MemberService {
    private final MemberSearchRepository memberSearchRepository;

    public void save(Member member) {
        Member savedMember = memberSearchRepository.save(member);
        log.info("id : {}",savedMember.getId());
        log.info("name : {}",savedMember.getName());
    }

    public List<Member> findAll() {
        Iterable<Member> documents = memberSearchRepository.findAll();
        return StreamSupport.stream(documents.spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<Member> findByMemberName(String memberName) {
         return memberSearchRepository.searchByMemberName(memberName);
    }

    public List<Member> findByMemberHome(String memberHome) {
        return memberSearchRepository.searchByMemberHome(memberHome);
    }


    public List<Member> findNameAndHome(String memberName, String memberHome) {
        return memberSearchRepository.searchByMemberNameAndHome(memberName, memberHome);
    }
}

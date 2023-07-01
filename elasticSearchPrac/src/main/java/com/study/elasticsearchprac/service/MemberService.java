package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.domain.search.MemberSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public void saveSave() {
        List<String> name = new ArrayList<>();
        List<String> home = new ArrayList<>();
        name.add("kim");
        name.add("lee");
        name.add("park");
        name.add("choi");
        name.add("jeong");
        name.add("shin");
        name.add("jeon");
        name.add("hong");
        name.add("cho");
        name.add("oh");
        home.add("seoul");
        home.add("daegu");
        home.add("daejeon");
        home.add("jaeju");
        home.add("gangwon");
        home.add("gwangju");
        home.add("incheon");
        home.add("busan");
        home.add("ulsan");
        home.add("newYork");
//        for (int i = 0; i < 100000; i++) {
//            Collections.shuffle(name);
//            Collections.shuffle(home);
//            Member build = Member.builder()
//                    .name(name.get(0))
//                    .age(String.valueOf(i))
//                    .home(home.get(0))
//                    .build();
//            memberSearchRepository.save(build);
//        }

    }
}

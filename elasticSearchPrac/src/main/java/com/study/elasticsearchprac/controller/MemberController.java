package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.domain.search.MemberSearchRepository;
import com.study.elasticsearchprac.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController @Slf4j @RequiredArgsConstructor
@RequestMapping("/search")
public class MemberController {
    private final MemberService memberService;
    private final MemberSearchRepository memberSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @GetMapping("/{userName}")
    public List<Member> findByUserName(@PathVariable(name = "userName") String userName) {
        return memberService.findByUserName(userName);
    }

    @GetMapping("/all")
    public List<Member> getAllMember() {
        Iterable<Member> documents = memberSearchRepository.findAll();
        return StreamSupport.stream(documents.spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping("/insert")
    public void insertMember(@RequestBody Member member) {
        memberService.save(member);
    }
}

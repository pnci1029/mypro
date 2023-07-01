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

@RestController @Slf4j @RequiredArgsConstructor
@RequestMapping("/search")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/name/{memberName}")
    public List<Member> findByMemberName(@PathVariable(name = "memberName") String memberName) {
        return memberService.findByMemberName(memberName);
    }

    @GetMapping("/home/{memberHome}")
    public List<Member> findByMemberHome(@PathVariable(name = "memberHome") String memberHome) {
        return memberService.findByMemberHome(memberHome);
    }

    @GetMapping("/nameHome")
    public List<Member> findByMemberNameAndHome(@RequestParam(name = "memberName")String memberName,
                                                @RequestParam(name = "memberHome")String memberHome) {
        return memberService.findNameAndHome(memberName,memberHome);
    }

    @GetMapping("/all")
    public List<Member> getAllMember() {
        return memberService.findAll();
    }

    @PostMapping("/insert")
    public void insertMember(@RequestBody Member member) {
        memberService.save(member);
    }
}

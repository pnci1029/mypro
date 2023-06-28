package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @Slf4j @RequiredArgsConstructor
@RequestMapping("/search")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{userName}")
    public List<Member> findByUserName(@PathVariable(name = "userName") String userName) {
        return memberService.findByUserName(userName);
    }

    @PostMapping("/insert")
    public void insertMember(@RequestBody Member member) {
        memberService.save(member);
    }
}

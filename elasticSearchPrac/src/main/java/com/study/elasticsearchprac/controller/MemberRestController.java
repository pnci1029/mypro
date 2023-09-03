package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/rest")
@RestController @RequiredArgsConstructor @Slf4j
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/")
    public List<Member> getAllMember(Model model) {
        return memberService.getMember();
    }


    @GetMapping("/name/{memberName}")
    public List<Member> getMemberName(@PathVariable(name = "memberName") String memberName) {
        return memberService.getMemberName(memberName);
    }

    @GetMapping("/age")
    public List<Member> getMemberAgeRange(@RequestParam(name = "memberAge") String memberAge) {
        return memberService.getMemberAge(memberAge);
    }

    @PostMapping("/create/member")
    public String postMember(@RequestBody Member member) {
        return memberService.postMember(member);
    }

    @PostMapping("/creates")
    public Long postMembers() {
        return memberService.postMembers();
    }

    @GetMapping("/aggregate/gender")
    public Map<String,String> aggregateByMemberGender() {
        return memberService.aggregateMemberGender();
    }
}

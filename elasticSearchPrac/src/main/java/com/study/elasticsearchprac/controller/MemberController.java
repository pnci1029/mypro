package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String getAllMember(Model model) {
        model.addAttribute("member", memberService.getMember());
        model.addAttribute("memberSize", memberService.getMember().size());
        return "main";
    }


    @GetMapping("/{memberName}")
    public String getMemberName(@PathVariable(name = "memberName") String memberName,
                                Model model) {
//        return memberService.getMemberName(memberName);
        System.out.println("memberName = " + memberName);

        model.addAttribute("member", memberService.getMemberName(memberName));
        model.addAttribute("memberSize", memberService.getMemberName(memberName).size());
        return "main";
    }

    @GetMapping("/age")
    public String getMemberAgeRange(@RequestParam(name = "memberAge") String memberAge,
                                    Model model) {
        model.addAttribute("member", memberService.getMemberAge(memberAge));
        model.addAttribute("memberSize", memberService.getMemberAge(memberAge).size());
        return "main";
    }


    @PostMapping("/create/member")
    public String postMember(@RequestBody Member member) {
        return memberService.postMember(member);
    }

    @PostMapping("/creates")
    public String postMembers() {
//        return memberService.postMembers();
        memberService.postMembers();

        return "redirect:/";
    }

    @GetMapping("/aggregate/gender")
    public String aggregateByMemberGender(Model model) {

        model.addAttribute("aggregate", memberService.aggregateMemberGender());
        Map<String, String> map = memberService.aggregateMemberGender();
        for (String s : map.keySet()) {
            System.out.println("s = " + s);
        }

        return "aggregate";
    }

}

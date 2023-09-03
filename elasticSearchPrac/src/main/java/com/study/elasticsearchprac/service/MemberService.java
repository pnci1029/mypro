package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.domain.search.MemberSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor @Slf4j
public class MemberService {
    private final MemberSearchRepository memberSearchRepository;

    public List<Member> getMember() {
        List<Member> result = memberSearchRepository.findAllMember();
        log.info(String.valueOf(result.size()));
        return result;
    }

    public List<Member> getMemberName(String memberName) {
//        List<Member> result = memberSearchRepository.findMemberByName(memberName);
        List<Member> result = memberSearchRepository.searchByMemberName(memberName);
        log.info(String.valueOf(result.size()));
        return result;
    }

    public List<Member> getMemberAge(String memberAge) {
        List<Member> memberAgeResult = memberSearchRepository.getMemberAge(memberAge);
        log.trace(String.valueOf(memberAgeResult.size()));
        return memberAgeResult;
    }

    public String postMember(Member member) {
        Member save = memberSearchRepository.save(member);
        log.info(save.getId());
        return "success";
    }

    public Long postMembers() {
        List<String> name = new ArrayList<>() {{
            add("kim");
            add("lee");
            add("park");
            add("choi");
            add("kang");
            add("you");
            add("jeong");
            add("hong");
            add("ahn");
            add("jeon");
            add("cho");
            add("oh");
        }};
        List<Integer> age = new ArrayList<>() {{
            add(10);
            add(15);
            add(20);
            add(25);
            add(30);
            add(35);
            add(40);
            add(45);
            add(50);
            add(55);
            add(60);
            add(65);
        }};
        List<String> home = new ArrayList<>() {{
            add("seoul");
            add("daegu");
            add("daejeon");
            add("busan");
            add("jaeju");
            add("gangwon");
            add("gwangju");
            add("incheon");
            add("newYork");
            add("tokyo");
        }};
        List<String> gender = new ArrayList<>() {{
            add("male");
            add("female");
        }};


        for (int i = 0; i < 100; i++) {
            Collections.shuffle(name);
            Collections.shuffle(age);
            Collections.shuffle(home);
            Collections.shuffle(gender);
            memberSearchRepository.save(Member.builder().name(name.get(0))
                    .age(String.valueOf(age.get(0)))
                    .gender(gender.get(0))
                    .home(home.get(0))
                    .build());
        }
        return memberSearchRepository.count();

    }

    public Map<String, String> aggregateMemberGender() {
        List<? extends Terms.Bucket> buckets = memberSearchRepository.aggregateMemberGender(memberSearchRepository.findAllMember());
        Map<String, String> map = new HashMap<>();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            map.put(key, String.valueOf(docCount));
        }
        return map;
    }


}

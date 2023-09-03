package com.study.elasticsearchprac.domain.search;

import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.util.List;

public interface MemberSearchRepositoryCustom {
    List<Member> searchByMemberName(String name);

    List<Member> findAllMember();

    List<Member> getMemberAge(String memberAge);

    List<? extends Terms.Bucket> aggregateMemberGender(List<Member> m);

    List<Member> searchByMemberHome(String home);

    List<Member> searchByMemberNameAndHome(String name, String home);

    List<Member> getMemberByMemberNameAndHome(String name);

}

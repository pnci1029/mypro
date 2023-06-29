package com.study.elasticsearchprac.domain.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSearchRepository extends ElasticsearchRepository<Member, String>, MemberSearchRepositoryCustom {
    List<Member> findMemberByName(String name);

    List<Member> findMemberByAgeAndHome(String name, String home);
}

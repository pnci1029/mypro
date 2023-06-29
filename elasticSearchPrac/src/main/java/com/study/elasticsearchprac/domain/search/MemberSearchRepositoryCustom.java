package com.study.elasticsearchprac.domain.search;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberSearchRepositoryCustom {
    List<Member> searchByMemberName(String name);
}

package com.study.elasticsearchprac.domain.search;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MemberSearchRepositoryImpl implements MemberSearchRepositoryCustom{
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Member> searchByMemberName(String name) {
        Criteria criteria = Criteria.where("name").contains(name);
        Query query = new CriteriaQuery(criteria);
        SearchHits<Member> search = elasticsearchOperations.search(query, Member.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> searchByMemberHome(String home) {
        CriteriaQuery target = new CriteriaQuery(Criteria.where("home").contains(home));
        SearchHits<Member> search = elasticsearchOperations.search(target, Member.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public List<Member> searchByMemberNameAndHome(String name, String home) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(Criteria.where("name").contains(name).and("home").contains(home));
//        CriteriaQuery criteriaQuery = new CriteriaQuery(Criteria.where("name").contains(name).and("home").matches(home));
        SearchHits<Member> search = elasticsearchOperations.search(criteriaQuery, Member.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}

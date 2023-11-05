package com.study.elasticsearchprac.domain.test;

import com.study.elasticsearchprac.domain.search.Member;
import com.study.elasticsearchprac.dto.MemberJobResponseDto;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JobSearchRepositoryImpl implements JobSearchRepositoryCustom {
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public MemberJobResponseDto searchMemberAndJobByValue(String value) {
        QueryBuilder query = QueryBuilders.queryStringQuery("*" + value + "*")
                .field("name")
                .field("home")
                .field("age")
                .field("money");
        NativeSearchQueryBuilder member = new NativeSearchQueryBuilder()
                .withQuery(query)
                ;

        NativeSearchQueryBuilder job = new NativeSearchQueryBuilder()
                .withQuery(query);

        SearchHits<Member> members = elasticsearchOperations.search(member.build(), Member.class);
        SearchHits<Job> searchs = elasticsearchOperations.search(job.build(), Job.class);
        List<Member> collectMember = members.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        List<Job> collectJob = searchs.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return MemberJobResponseDto.builder()
                .jobList(collectJob)
                .memberList(collectMember)
                .build();

//        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(value, "name", "home", "money");
//
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQuery);
//
//        SearchHits<Member> memberHits = elasticsearchOperations.search(queryBuilder.build(), Member.class);
//        List<Member> collectMember = memberHits.stream()
//                .map(SearchHit::getContent)
//                .collect(Collectors.toList());
//
//        SearchHits<Job> jobHits = elasticsearchOperations.search(queryBuilder.build(), Job.class);
//        List<Job> collectJob = jobHits.stream()
//                .map(SearchHit::getContent)
//                .collect(Collectors.toList());
//        return MemberJobResponseDto.builder()
//                .jobList(collectJob)
//                .memberList(collectMember)
//                .build();
    }
}

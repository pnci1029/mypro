package com.study.elasticsearchprac.domain.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor @Slf4j
public class MemberSearchRepositoryImpl implements MemberSearchRepositoryCustom{

    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final RestHighLevelClient client;

    @Override
    public List<Member> findAllMember() {
        Criteria criteria = new Criteria();
        Query query = new CriteriaQuery(criteria);
        SearchHits<Member> search = elasticsearchOperations.search(query, Member.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> getMemberAge(String memberAge) {
        int targetAge = Integer.parseInt(memberAge);

        RangeQueryBuilder age = QueryBuilders.rangeQuery("age")
                .gte(targetAge)
                .lt(targetAge + 10);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(age)
                .withPageable(PageRequest.of(0,1000))
                .build();

        SearchHits<Member> search = elasticsearchRestTemplate.search(searchQuery, Member.class);

        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> getMemberByMemberNameAndHome(String name) {
        return null;
    }

    @Override
    public List<? extends Terms.Bucket> aggregateMemberGender(List<Member> memberList) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .aggregation(AggregationBuilders.terms("gender.keyword").field("gender"))
                .size(0);

        SearchRequest searchRequest = new SearchRequest("member");
        searchRequest.source(searchSourceBuilder);

        SearchResponse response;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Aggregations aggregations = response.getAggregations();
        List<Aggregation> aggregations1 = aggregations.asList();
        for (Aggregation aggregation : aggregations1) {
            System.out.println(aggregation.getName());
        }
        ParsedStringTerms genderAggregation = aggregations.get("gender.keyword");
        List<? extends Terms.Bucket> buckets = genderAggregation.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println("Gender: " + key + ", Doc Count: " + docCount);
        }


        return buckets;
    }

    @Override
    public List<Member> searchByMemberName(String name) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", name))
                .should(QueryBuilders.matchQuery("home", name));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(PageRequest.of(0,1000))
                .build();


//        CriteriaQuery criteria = new CriteriaQuery(
//                Criteria.where("name").contains(name)
//                        .and("home").contains(name));

//        Criteria criteria = Criteria.where("name").contains(name);
//        Query query = new CriteriaQuery(criteria);

        SearchHits<Member> search = elasticsearchOperations.search(searchQuery, Member.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> searchByMemberHome(String home) {
        return null;
    }

    @Override
    public List<Member> searchByMemberNameAndHome(String name, String home) {
        return null;
    }

    public SearchSourceBuilder searchSourceBuilder(String gender) {
        BoolQueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("gender", gender));

        TermsAggregationBuilder gender1 = AggregationBuilders.terms("gender");

        return SearchSourceBuilder.searchSource().query(query).aggregation(gender1).size(0);
    }

}

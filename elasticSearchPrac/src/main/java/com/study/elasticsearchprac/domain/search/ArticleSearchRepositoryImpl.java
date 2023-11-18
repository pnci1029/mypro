package com.study.elasticsearchprac.domain.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class ArticleSearchRepositoryImpl implements ArticleSearchRepositoryCustom {

    private final ElasticsearchOperations elasticsearchOperations;
    @Override
    public List<ArticleSearch> findAllArticles() {
        Criteria criteria = new Criteria();
        Query query = new CriteriaQuery(criteria);
        SearchHits<ArticleSearch> search = elasticsearchOperations.search(query, ArticleSearch.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleSearch> findArticleByImage(String imgName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("img", imgName))
                .should(QueryBuilders.matchQuery("imgTagging",imgName));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(PageRequest.of(0,1000))
                .build();

        SearchHits<ArticleSearch> search = elasticsearchOperations.search(searchQuery, ArticleSearch.class);
        return search.stream()
                .map(SearchHit::getContent)
                .findFirst();
    }

    @Override
    public void addTagging(List<String> imgTagging) {

    }

    @Override
    public Optional<ArticleSearch> findArticle(String articleId) {
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
//                .must(QueryBuilders.matchQuery("id", articleId));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.idsQuery().addIds(articleId))
                .withPageable(PageRequest.of(0, 1000))
                .build();

        SearchHits<ArticleSearch> search = elasticsearchOperations.search(searchQuery, ArticleSearch.class);
        return search.stream()
                .map(SearchHit::getContent)
                .findFirst();
    }

    @Override
    public List<ArticleSearch> searchArticleByKeyword(String keyword) {
        SearchHits<ArticleSearch> search = elasticsearchOperations.search(
                new NativeSearchQueryBuilder()
                        .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content", "imgTagging"))
                        .withPageable(PageRequest.of(0, 1000))
                        .build(),
                ArticleSearch.class
        );

        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());


//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
//                .should(QueryBuilders.matchQuery("title", keyword))
//                .should(QueryBuilders.matchQuery("content",keyword))
//                .should(QueryBuilders.matchQuery("imgTagging",keyword))
//                ;
//
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(boolQuery)
//                .withPageable(PageRequest.of(0,1000))
//                .build();
//
//        SearchHits<ArticleSearch> search = elasticsearchOperations.search(searchQuery, ArticleSearch.class);
//        return search.stream()
//                .map(SearchHit::getContent)
//                .collect(Collectors.toList());
    }

    @Override
    public ArticleSearch searchArticleTags(String imgId) {
        SearchHits<ArticleSearch> search = elasticsearchOperations.search(
                new NativeSearchQueryBuilder()
                        .withQuery(QueryBuilders.matchQuery(imgId,"id"))
                        .withPageable(PageRequest.of(0, 1000))
                        .build(),
                ArticleSearch.class
        );
        return search.isEmpty() ? null : search.getSearchHit(0).getContent();
    }
}

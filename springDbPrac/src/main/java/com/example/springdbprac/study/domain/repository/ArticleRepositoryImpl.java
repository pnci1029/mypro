package com.example.springdbprac.study.domain.repository;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.QArticle;
import com.example.springdbprac.study.domain.entity.QComment;
import com.example.springdbprac.study.dto.responseDto.ArticleResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springdbprac.study.domain.entity.QArticle.article;
import static com.example.springdbprac.study.domain.entity.QComment.comment;

@RequiredArgsConstructor @Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<ArticleResponseDto> findAllArticles(Pageable pageable) {
        List<Article> fetch = jpaQueryFactory
                .selectFrom(article)
                .innerJoin(article).on(comment.article.eq(article)).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        List<ArticleResponseDto> result = articleToDto(fetch);

        boolean hasNext = false;
        if (result.size() > pageable.getPageSize()) {
            result.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(result, pageable, hasNext);
    }

    @Override
    public Slice<ArticleResponseDto> searchArticles(Long id, String title, String contents, Pageable pageable) {

        List<Article> fetch = jpaQueryFactory
                .selectFrom(article)
                .innerJoin(article).on(comment.article.eq(article)).fetchJoin()
                .where(article.id.like(String.valueOf(id)).or(article.title.like("%"+title+"%")).or(article.contents.like("%"+contents+"%")))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        List<ArticleResponseDto> result = articleToDto(fetch);
        boolean hasNext = false;
        if (result.size() > pageable.getPageSize()) {
            result.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(result, pageable, hasNext);
    }


    private static List<ArticleResponseDto> articleToDto(List<Article> fetch) {
        return fetch.stream()
                .map(target -> ArticleResponseDto.builder()
                        .id(target.getId())
                        .title(target.getTitle())
                        .contents(target.getContents())
                        .articleStatus(target.getArticleStatus())
                        .comments(target.getComments())
                        .view(target.getView())
                        .build())
                .collect(Collectors.toList());
    }
}

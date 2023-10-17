package com.example.springdbprac.study.service;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import com.example.springdbprac.study.dto.requestDto.ArticleRequestDto;
import com.example.springdbprac.study.dto.responseDto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(ArticleRequestDto dto) {
        articleRepository.save(Article.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .articleStatus(ArticleStatus.valueOf(dto.getArticleStatus()))
                .build());
    }

    public Article findArticle(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            article.get().viewIncrease();
            articleRepository.save(article.get());
            return article.get();
        } else {
            throw new NoSuchElementException("not found such article");
        }
    }

    public Slice<ArticleResponseDto> findArticles(Pageable pageable) {
        return articleRepository.findAllArticles(pageable);
    }

    public Slice<ArticleResponseDto> searchArticles(Long id, String title, String contents, Pageable pageable) {
        return articleRepository.searchArticles(id, title, contents, pageable);
    }

    public Article searchArticleTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}

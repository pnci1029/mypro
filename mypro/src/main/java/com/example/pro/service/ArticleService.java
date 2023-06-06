package com.example.pro.service;

import com.example.pro.domain.entity.Article;
import com.example.pro.domain.entity.Member;
import com.example.pro.domain.repository.ArticleRepository;
import com.example.pro.domain.repository.MemberRepository;
import com.example.pro.dto.article.articleRequestDto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public Article postArticle(@Valid ArticleRequestDto articleRequestDto) {

        Article data = Article.builder()
                .title(articleRequestDto.getTitle())
                .content(articleRequestDto.getContent())
                .sq(articleRequestDto.getSq())
                .bc(articleRequestDto.getBc())
                .dl(articleRequestDto.getDl())
                .build();
        return articleRepository.save(data);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void patchArticle(@Valid ArticleRequestDto articleRequestDto) {

    }
}

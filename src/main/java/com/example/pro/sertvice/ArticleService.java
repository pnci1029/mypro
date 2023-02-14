package com.example.pro.sertvice;

import com.example.pro.domain.entity.Article;
import com.example.pro.domain.entity.Member;
import com.example.pro.domain.repository.ArticleRepository;
import com.example.pro.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    public void postArticle() {

    }
}

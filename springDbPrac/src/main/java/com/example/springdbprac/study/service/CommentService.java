package com.example.springdbprac.study.service;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.Comment;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import com.example.springdbprac.study.domain.repository.CommentRepository;
import com.example.springdbprac.study.dto.requestDto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor @Slf4j @Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public void create(CommentRequestDto commentRequestDto) {
        Optional<Article> article = articleRepository.findById(commentRequestDto.getArticleId());
        Comment comment;
        if (article.isEmpty()) {
            throw new NoSuchElementException("no such Element");
        } else {
            comment = Comment.builder()
                    .article(article.get())
                    .writer(commentRequestDto.getWriter())
                    .contents(commentRequestDto.getContents())
                    .build();

        }
        commentRepository.save(comment);
    }
}

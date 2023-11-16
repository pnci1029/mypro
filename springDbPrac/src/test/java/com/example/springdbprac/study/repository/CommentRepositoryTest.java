package com.example.springdbprac.study.repository;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.entity.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    com.example.springdbprac.study.domain.repository.CommentRepository commentRepository;

    Article article;

    @BeforeEach
    void beforeEach() {
        article = Article.builder()
                .id(1L)
                .title("title")
                .contents("contents")
                .view(0)
                .articleStatus(ArticleStatus.Entertainment)
                .build();
    }

    @Test
    void commentCreate() {
        commentRepository.save(Comment.builder()
                .id(1L)
                .writer("writer")
                .contents("contents")
                .article(article)
                .build())
        ;

        Assertions.assertThat(commentRepository.count()).isEqualTo(1L);
    }
}

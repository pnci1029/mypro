package com.example.springdbprac.study.repository;

import com.example.springdbprac.study.domain.entity.Article;
import com.example.springdbprac.study.domain.entity.ArticleStatus;
import com.example.springdbprac.study.domain.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @BeforeEach
    void beforeEach() {
        for (int i = 0; i < 10; i++) {
            articleRepository.save(Article.builder()
                    .title("title" + i + "test")
                    .contents("contents" + i + "test")
                    .view(0)
                    .articleStatus(ArticleStatus.Health)
                    .build());
        }
    }

    @DisplayName("id로 게시글 조회 O")
    @Test
    void findByArticleIdO() {
        Optional<Article> article = articleRepository.findById(1L);
        assertThat(article.get()).isNotNull();

    }

    @DisplayName("id로 게시글 조회 X")
    @Test
    void findByArticleIdX() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    articleRepository.findById(500L).orElseThrow(NoSuchElementException::new);
                });

    }


    @DisplayName("전체 조회")
    @Test
    void findAllArticles() {
        assertThat(articleRepository.findAll().size()).isEqualTo(10);
    }
}

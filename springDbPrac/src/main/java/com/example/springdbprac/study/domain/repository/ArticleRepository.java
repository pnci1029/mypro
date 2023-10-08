package com.example.springdbprac.study.domain.repository;

import com.example.springdbprac.study.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
}

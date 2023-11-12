package com.study.elasticsearchprac.domain.search;

import java.util.List;

public interface ArticleSearchRepositoryCustom {

    List<ArticleSearch> findAllArticles();
}

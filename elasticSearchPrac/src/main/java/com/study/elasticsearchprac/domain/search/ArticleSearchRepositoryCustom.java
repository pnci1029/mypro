package com.study.elasticsearchprac.domain.search;

import com.study.elasticsearchprac.dto.ImageTagResponseDto;

import java.util.List;
import java.util.Optional;

public interface ArticleSearchRepositoryCustom {

    List<ArticleSearch> findAllArticles();

    Optional<ArticleSearch> findArticleByImage(String imgName);

    void addTagging(List<String> imgTagging);

    Optional<ArticleSearch> findArticle(String articleId);

    List<ArticleSearch> searchArticleByKeyword(String keyword);

    ArticleSearch searchArticleTags(String imgId);

    List<ImageTagResponseDto> getAllImageInfos();
}

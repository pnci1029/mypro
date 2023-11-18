package com.study.elasticsearchprac.service;

import com.study.elasticsearchprac.domain.search.ArticleSearch;
import com.study.elasticsearchprac.domain.search.ArticleSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class ArticleSearchService {

    private final ArticleSearchRepository articleSearchRepository;
    public List<ArticleSearch> getAllArticles() {
        return articleSearchRepository.findAllArticles();
    }

    public void tagImage(List<String> tagging, String img) {
        Optional<ArticleSearch> articleSearch = articleSearchRepository.findArticleByImage(img);
        if (articleSearch.isPresent()) {
            log.info(articleSearch.get().getTitle());
            log.info(articleSearch.get().getId());

            // 전체 태깅 데이터
            log.info("all tagging : {}",String.valueOf(articleSearch.get().getImgTagging()));

            List<String> imgTagging = new ArrayList<>();
            imgTagging.addAll(articleSearch.get().getImgTagging());
            imgTagging.addAll(tagging);
            ArticleSearch targetArticle = ArticleSearch.builder()
                    .id(articleSearch.get().getId())
                    .title(articleSearch.get().getTitle())
                    .content(articleSearch.get().getContent())
                    .img(articleSearch.get().getImg())
                    .imgTagging(imgTagging)
                    .build();
//            articleSearchRepository.addTagging(tagging);
            articleSearchRepository.save(targetArticle);

            if (!articleSearch.get().getImgTagging().isEmpty() ||articleSearch.get().getImgTagging() != null) {
                for (String s : articleSearch.get().getImgTagging()) {
                    log.info("tagging : {}",s);
                }
            }
        }
    }

    public ArticleSearch getArticleById(String articleId) {
        if (articleSearchRepository.findArticle(articleId).isPresent()) {
            return articleSearchRepository.findArticle(articleId).get();
        } else {
            throw new NoSuchElementException("실패");
        }

    }

    public List<ArticleSearch> searchArticle(String keyword) {
        return articleSearchRepository.searchArticleByKeyword(keyword);
    }

    public ArticleSearch searchArticleTag(String imgId) {
        return articleSearchRepository.searchArticleTags(imgId);
    }
}

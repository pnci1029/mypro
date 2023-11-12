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

    public ArticleSearch searchArticle(String articleId) {
        if (articleSearchRepository.findArticle(articleId).isPresent()) {
            return articleSearchRepository.findArticle(articleId).get();
        } else {
            throw new NoSuchElementException("실패");
        }

    }
}

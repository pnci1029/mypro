package com.example.springdbprac.study.domain.repository;

import com.example.springdbprac.study.dto.responseDto.ArticleResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArticleRepositoryCustom {
    Slice<ArticleResponseDto> findAllArticles(Pageable pageable);
    Slice<ArticleResponseDto> searchArticles(Long id, String title, String contents, Pageable pageable);
}

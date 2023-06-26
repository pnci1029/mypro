package com.example.pro.pracPackage.elasticSearch.search;

import com.example.pro.pracPackage.elasticSearch.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserSearchRepository {
    List<User> searchByName(String name, Pageable pageable);
}

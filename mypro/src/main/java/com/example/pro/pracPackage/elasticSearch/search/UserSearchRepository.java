package com.example.pro.pracPackage.elasticSearch.search;

import java.util.List;

import com.example.pro.pracPackage.elasticSearch.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSearchRepository extends ElasticsearchRepository<User, Long>, CustomUserSearchRepository {

    List<User> findByBasicProfile_NameContains(String name);
}
package com.example.pro.pracPackage.elasticSearch.domain;

import com.example.pro.pracPackage.elasticSearch.BasicProfile;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

/**
 * JPA가 RDB에 매핑하는것을 Entity로 명시하는것처럼
 * Document 어노테이션을 통해 Es DB에 매핑하는것을 명시
 */
@Document(indexName = "users")
@Entity(name = "USERS") @Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", insertable = false, updatable = false)
    private String name;
    @Column(name = "description", insertable = false, updatable = false)
    private String description;

    @Embedded
    private BasicProfile basicProfile;

    protected User() {
    }

    public User(String name) {
        this(name, null);
    }

    public User(String name, String description) {
        this(null, new BasicProfile(name, description));
    }

    @PersistenceConstructor
    public User(Long id, BasicProfile basicProfile) {
        this.id = id;
        this.basicProfile = basicProfile;
    }
}

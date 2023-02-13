package com.example.pro.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "member")
    private List<Article> article;

}

package com.example.pro.pracPackage.elasticSearch;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data @Builder
public class BasicProfile {
    @Column(nullable = false)
    private String name;

    private String description;

    protected BasicProfile() {
    }

    public BasicProfile(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

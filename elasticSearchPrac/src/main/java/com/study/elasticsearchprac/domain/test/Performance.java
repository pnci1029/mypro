package com.study.elasticsearchprac.domain.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor @Builder
@AllArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String dateTime;
    private String length;
    private String imageLink;
    @Enumerated(EnumType.STRING)
    private PerformanceType performanceType;

}

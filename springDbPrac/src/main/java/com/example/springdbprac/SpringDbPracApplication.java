package com.example.springdbprac;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableJpaAuditing
@SpringBootApplication
public class SpringDbPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDbPracApplication.class, args);
    }
    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory JPAQueryFactory() {
        //
        return new JPAQueryFactory(em);
    }

}

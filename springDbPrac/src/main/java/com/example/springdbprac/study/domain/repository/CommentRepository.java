package com.example.springdbprac.study.domain.repository;

import com.example.springdbprac.study.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

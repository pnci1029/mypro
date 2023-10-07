package com.example.springdbprac.study.controller;

import com.example.springdbprac.study.dto.requestDto.CommentRequestDto;
import com.example.springdbprac.study.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public void createComment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.create(commentRequestDto);
    }
}

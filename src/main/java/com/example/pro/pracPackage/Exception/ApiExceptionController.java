package com.example.pro.pracPackage.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/member/get/{id}")
    public MemberDto getMember(@PathVariable(name = "id") String id) {
        if (id.equals("Ex")) {
            throw new RuntimeException("예외 발생");
        }
        return new MemberDto(id, "name + "+ id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;

    }
}

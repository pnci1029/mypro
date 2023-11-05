package com.study.elasticsearchprac.dto;

import com.study.elasticsearchprac.domain.test.Job;
import com.study.elasticsearchprac.domain.search.Member;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data @Builder
public class MemberJobResponseDto {
    List<Member> memberList = new ArrayList<>();
    List<Job> jobList = new ArrayList<>();
}

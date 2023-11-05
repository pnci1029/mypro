package com.study.elasticsearchprac.domain.test;

import com.study.elasticsearchprac.dto.MemberJobResponseDto;

public interface JobSearchRepositoryCustom {
    MemberJobResponseDto searchMemberAndJobByValue(String value);
}

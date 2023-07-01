package com.study.elasticsearchprac.domain.search;

import com.study.elasticsearchprac.dto.MemberJobResponseDto;

public interface JobSearchRepositoryCustom {
    MemberJobResponseDto searchMemberAndJobByValue(String value);
}

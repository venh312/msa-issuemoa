package com.issuemoa.learning.domain.interview;

import com.issuemoa.learning.presentation.dto.InterviewResponse;

import java.util.List;

public interface InterviewRepositoryCustom {
    List<InterviewResponse> findByCategoryOrderById(String category, String sort);
}

package com.issuemoa.learning.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record InterviewResponse(
        @Schema(description = "IDX") Long id,
        @Schema(description = "카테고리") String category,
        @Schema(description = "질문") String question,
        @Schema(description = "답변") String answer) {
}

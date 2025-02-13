package com.issuemoa.learning.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record VocaRetryResponse(
        @Schema(description = "IDX") Long id,
        @Schema(description = "단어") String word,
        @Schema(description = "의미") String mean,
        @Schema(description = "USER ID") Long userId,
        @Schema(description = "학습 여부 (Y/N)") String learnYn) {
}

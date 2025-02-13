package com.issuemoa.learning.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record VocaLearnResponse(
        @Schema(description = "IDX") Long id,
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "Voca ID") Long vocaId,
        @Schema(description = "학습 여부 (Y/N)") String learnYn) {
}

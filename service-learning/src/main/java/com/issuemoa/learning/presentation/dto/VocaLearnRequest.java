package com.issuemoa.learning.presentation.dto;

import com.issuemoa.learning.domain.voca.learn.VocaLearn;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(name="Voca Learn Request")
public record VocaLearnRequest(
        @Schema(description = "IDX") Long id,
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "Voca ID")
        @NotNull(message = "필수 입력 값입니다.")
        Long vocaId,
        @Schema(description = "학습 여부")
        @Pattern(regexp = "Y|N", message = "Y 또는 N 값만 허용 합니다.")
        String learnYn) {
    public VocaLearn toEntity(Long userId) {
        return VocaLearn.builder()
                .userId(userId)
                .vocaId(this.vocaId)
                .learnYn(this.learnYn)
                .registerId(userId)
                .modifyId(userId)
                .build();
    }
}

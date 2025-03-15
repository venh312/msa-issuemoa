package com.issuemoa.learning.presentation.dto;

import com.issuemoa.learning.domain.BaseTime;
import com.issuemoa.learning.domain.grade.GradeExp;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "Grade Exp Response")
public record GradeExpResponse(
        @Schema(description = "IDX") Long id,
        @Schema(description = "등급 코드") String gradeCode,
        @Schema(description = "등급 달성 목표") int standard,
        @Schema(description = "등록시간") LocalDateTime registerTime,
        @Schema(description = "변경시간") LocalDateTime modifyTime) {
    public String getRegisterTime() {
        return BaseTime.toStringDateTime(registerTime);
    }
    public String getModifyTime() {
        return BaseTime.toStringDateTime(modifyTime);
    }

    public static GradeExpResponse toDto(GradeExp gradeExp) {
        return new GradeExpResponse(
                gradeExp.getId(),
                gradeExp.getGradeCode(),
                gradeExp.getStandard(),
                gradeExp.getRegisterTime(),
                gradeExp.getModifyTime()
        );
    }

}

package com.issuemoa.learning.presentation.dto;

import com.issuemoa.learning.domain.interview.favorites.InterviewFavorites;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(name="Interview Favorites Request")
public record InterviewFavoritesRequest(
        @Schema(description = "인터뷰 IDX", required = true)
        @NotNull(message = "필수 입력 값입니다.")
        Long interviewId,

        @Schema(description = "사용 여부(Y/N)", required = true)
        @NotEmpty(message = "필수 입력 값 입니다.")
        @Pattern(regexp = "Y|N", message = "Y 또는 N 값만 허용 합니다.")
        String useYn
) {
    public InterviewFavorites toEntity(Long registerId) {
        return InterviewFavorites.builder()
                    .interviewId(interviewId)
                    .useYn(useYn)
                    .registerId(registerId)
                    .build();
    }
}

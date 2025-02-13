package com.issuemoa.board.presentation.dto;

import com.issuemoa.board.domain.BaseTime;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public record BoardResponse(
        @Schema(description = "IDX")
        @Id
        String id,

        @Schema(description = "news / youtube")
        String type,

        @Schema(description = "제목")
        String title,

        @Schema(description = "내용")
        String contents,

        @Schema(description = "URL")
        String url,

        @Schema(description = "썸네일")
        String thumbnail,

        @Schema(description = "등록시간")
        String registerDateTime
) {
    public String getRegisterDateTime(LocalDateTime registerDateTime) {
        return BaseTime.toStringDateTime(registerDateTime);
    }
}

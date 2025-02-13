package com.issuemoa.board.presentation.dto;

import com.issuemoa.board.domain.inquiry.Inquiry;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="InquirySaveRequest")
public record InquirySaveRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "이메일") String email,
        @Schema(description = "문의유형") String type,
        @Schema(description = "내용") String contents
) {
    public Inquiry toEntity() {
        return Inquiry.builder()
                .name(name)
                .email(email)
                .type(type)
                .contents(contents)
                .build();
    }
}

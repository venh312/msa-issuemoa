package com.issuemoa.board.presentation.dto;

import com.issuemoa.board.domain.inquiry.Inquiry;

public record InquirySaveResponse(
        Long id,
        String name,
        String email,
        String type,
        String contents) {

    public static InquirySaveResponse toDto(Inquiry inquiry) {
        return new InquirySaveResponse(
                inquiry.getId(),
                inquiry.getName(),
                inquiry.getEmail(),
                inquiry.getType(),
                inquiry.getContents()
        );
    }
}

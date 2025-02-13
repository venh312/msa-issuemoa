package com.issuemoa.board.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public record BoardFavoritesSearch(
        @Schema(description = "사용자 ID", required = true) String userId) {
    public BoardFavoritesSearch {
        Assert.hasText(userId, "사용자 ID는 필수입니다.");
    }
}

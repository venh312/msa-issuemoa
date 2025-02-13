package com.issuemoa.board.presentation.dto;

import com.issuemoa.board.domain.BaseTime;
import com.issuemoa.board.domain.board.favorite.BoardFavorites;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import java.util.List;

public record BoardFavoritesResponse(
        @Schema(description = "IDX")
        @Id
        String id,
        @Schema(description = "Board ID")
        String boardId,
        @Schema(description = "사용자 ID")
        Long userId,
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
        LocalDateTime registerDateTime) {
    public String getRegisterDateTime(LocalDateTime registerDateTime) {
        return BaseTime.toStringDateTime(registerDateTime);
    }

    public static BoardFavoritesResponse toDto(BoardFavorites boardFavorites) {
        return new BoardFavoritesResponse(
                boardFavorites.getId(),
                boardFavorites.getBoardId(),
                boardFavorites.getUserId(),
                boardFavorites.getType(),
                boardFavorites.getTitle(),
                boardFavorites.getContents(),
                boardFavorites.getUrl(),
                boardFavorites.getThumbnail(),
                boardFavorites.getRegisterDateTime()
        );
    }
}

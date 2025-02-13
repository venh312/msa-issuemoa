package com.issuemoa.board.presentation.dto;

import com.issuemoa.board.domain.board.favorite.BoardFavorites;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
public record BoardFavoritesSave(
        @Schema(description = "Board ID", defaultValue = "67a6d6c073c38e7abe5bbc11")
        String id,
        @Schema(description = "news / youtube", defaultValue = "news")
        String type,
        @Schema(description = "제목", defaultValue = "김용현 뺏길까 검찰총장까지 움직였다, 커지는 '비화폰 수사 방해' 의혹")
        String title,
        @Schema(description = "내용", defaultValue = "")
        String contents,
        @Schema(description = "URL", defaultValue = "https://n.news.naver.com/article/047/0002461928?ntype=RANKING")
        String url,
        @Schema(description = "썸네일", defaultValue = "https://mimgnews.pstatic.net/image/origin/047/2025/02/07/2461928.jpg?type=nf70_70")
        String thumbnail) {

        public BoardFavorites toEntity(Long userId) {
               return BoardFavorites.builder()
                       .boardId(this.id)
                       .userId(userId)
                       .type(this.type)
                       .title(this.title)
                       .contents(this.contents)
                       .url(this.url)
                       .thumbnail(this.thumbnail)
                       .registerDateTime(LocalDateTime.now())
                       .build();
        }
}

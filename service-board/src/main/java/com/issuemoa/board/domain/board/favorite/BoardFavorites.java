package com.issuemoa.board.domain.board.favorite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Schema(name = "Board Favorites Response")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Document(collection = "boardFavorites")
@Builder
public class BoardFavorites {
    @Schema(description = "IDX")
    @Id
    private String id;

    @Schema(description = "Board ID")
    String boardId;

    @Schema(description = "User ID")
    private Long userId;

    @Schema(description = "news / youtube")
    private String type;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String contents;

    @Schema(description = "URL")
    private String url;

    @Schema(description = "썸네일")
    private String thumbnail;

    @Schema(description = "등록시간")
    private LocalDateTime registerDateTime;
}
package com.issuemoa.board.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "Board Response")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Document(collection = "board")
public class Board {
    @Schema(description = "IDX")
    @Id
    private String id;

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
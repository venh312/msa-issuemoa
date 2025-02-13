package com.issuemoa.board.domain.keyword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Schema(name = "Keyword Response")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Document(collection = "keyword")
public class Keyword {
    @Schema(description = "IDX")
    @Id
    private String id;

    @Schema(description = "키워드")
    private String keyword;

    @Schema(description = "개수")
    private int count;

    @Schema(description = "기준 날짜 시간")
    private LocalDateTime baseDateTime;
}

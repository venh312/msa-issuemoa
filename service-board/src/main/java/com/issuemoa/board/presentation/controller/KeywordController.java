package com.issuemoa.board.presentation.controller;

import com.issuemoa.board.application.KeywordService;
import com.issuemoa.board.domain.keyword.Keyword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Keyword", description = "키워드 API")
@RequiredArgsConstructor
@RestController
public class KeywordController {
    private final KeywordService keywordService;

    @Operation(summary = "[TOP 10] 키워드 목록 조회", description = "제일 많이 노출되는 키워드 목록을 반환합니다.")
    @GetMapping("/keyword")
    public ResponseEntity<List<Keyword>> findAll(
                @Parameter(description = "값 1: (Today -1 Day), 2: (Today -2 Day) ...")
                @RequestParam(required = false, defaultValue = "1") int minusDay) {
        return ResponseEntity.ok(keywordService.findTop10ByBaseDateTimeOrderByCountDesc(minusDay));
    }
}

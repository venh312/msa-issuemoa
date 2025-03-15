package com.issuemoa.board.presentation.controller;

import com.issuemoa.board.application.BoardFavoritesService;
import com.issuemoa.board.presentation.dto.BoardFavoritesResponse;
import com.issuemoa.board.presentation.dto.BoardFavoritesSave;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "Board Favorites", description = "관심 이슈(NEWS / YOUTUBE) API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardFavoritesController {
    private final BoardFavoritesService boardFavoritesService;

    @Operation(summary = "관심 이슈 등록")
    @PostMapping("/board/favorites")
    public ResponseEntity<BoardFavoritesResponse> save(
            HttpServletRequest request,
            @RequestBody BoardFavoritesSave boardFavoritesSave){
        return ResponseEntity.ok(boardFavoritesService.save(request, boardFavoritesSave));
    }

    @Operation(summary = "관심 NEWS / YOUTUBE 목록 조회")
    @GetMapping("/board/favorites")
    public ResponseEntity<List<BoardFavoritesResponse>> findByUserId(HttpServletRequest request){
        return ResponseEntity.ok(boardFavoritesService.findByUserId(request));
    }

    @Operation(summary = "관심 NEWS / YOUTUBE 삭제")
    @DeleteMapping("/board/favorites/{id}")
    public ResponseEntity<String> deleteByIdAndUserId(
            HttpServletRequest request,
            @PathVariable String id){
            boardFavoritesService.deleteByIdAndUserId(request, id);
        return ResponseEntity.ok(id);
    }
}

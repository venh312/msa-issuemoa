package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.InterviewFavoritesService;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
public class InterviewFavoritesController {
    private final InterviewFavoritesService interviewFavoritesService;

    @Operation(summary = "인터뷰 관심 목록", description = "사용 여부(useYn) 이 Y인 인터뷰 관심 목록을 조회한다.")
    @GetMapping("/favorites")
    public ResponseEntity<Map> findByRegisterId(HttpServletRequest request) {
        return ResponseEntity.ok(interviewFavoritesService.findByRegisterId(request));
    }

    @Operation(summary = "인터뷰 관심 등록/해제", description = "인터뷰 관심 등록/해제한다. (이미 등록되어 있을 경우 사용 여부(useYn)를 변경한다.")
    @PostMapping("/favorites")
    public ResponseEntity<Long> findByRegisterId(
                HttpServletRequest request,
                @Valid @RequestBody InterviewFavoritesRequest interviewFavoritesRequest) {
        return ResponseEntity.ok(interviewFavoritesService.save(request, interviewFavoritesRequest));
    }

    @Operation(summary = "인터뷰 관심 삭제", description = "인터뷰 관심 목록을 삭제 한다.")
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<Long> deleteByIdAndRegisterId(
                HttpServletRequest request,
                @PathVariable Long id) {
        interviewFavoritesService.deleteByIdAndRegisterId(request, id);
        return ResponseEntity.ok(id);
    }
}

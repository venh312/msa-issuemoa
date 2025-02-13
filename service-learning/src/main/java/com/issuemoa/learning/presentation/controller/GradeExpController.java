package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.GradeExpService;
import com.issuemoa.learning.presentation.dto.GradeExpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Grade Exp", description = "등급 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class GradeExpController {
    private final GradeExpService gradeExpService;

    @Operation(summary = "Grade Exp 목록", description = "등급 달성 목록을 불러온다.")
    @GetMapping("/grade-exp")
    public ResponseEntity<List<GradeExpResponse>> findAll(){
        return ResponseEntity.ok(gradeExpService.findAll());
    }

    @Operation(summary = "Grade Exp 조회(standard)", description = "standard 에 맞는 등급 경험치 정보를 불러온다.")
    @GetMapping("/grade-exp/{standard}")
    public ResponseEntity<GradeExpResponse> findTop1ByStandardLessThanEqualOrderByStandardDesc(@PathVariable("standard") int standard){
        return ResponseEntity.ok(gradeExpService.findTop1ByStandardLessThanEqualOrderByStandardDesc(standard));
    }
}

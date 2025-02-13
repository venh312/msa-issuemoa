package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.VocaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Tag(name = "Voca", description = "Voca API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaController {
    private final VocaService vocaService;

    @Operation(summary = "Voca 목록", description = "Voca 목록을 불러온다.")
    @GetMapping("/voca")
    public ResponseEntity<HashMap<String, Object>> findAll(
        HttpServletRequest request,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "20") Integer limit){

        return ResponseEntity.ok(vocaService.findAll(request, offset, limit));
    }

    @Operation(summary = "단어 다시보기 목록", description = "단어 다시보기 목록을 불러온다.")
    @GetMapping("/voca/retry")
    public ResponseEntity<HashMap<String, Object>> findByVocaRetry(
            HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit){
        return ResponseEntity.ok(vocaService.findByVocaRetry(request, offset, limit));
    }
}

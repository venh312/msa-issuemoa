package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.VocaLearnService;
import com.issuemoa.learning.presentation.dto.VocaLearnRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "Voca Learn", description = "Voca 학습 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaLearnController {
    private final VocaLearnService vocaLearnService;

    @Operation(summary = "Voca 알고 있어요", description = "학습한 단어의 학습 여부를 등록한다.")
    @PostMapping("/voca-learn")
    public ResponseEntity<Long> save(
                HttpServletRequest request,
                @Valid @RequestBody VocaLearnRequest vocaLearnRequest){
        return ResponseEntity.ok(vocaLearnService.save(request, vocaLearnRequest));
    }

    @Operation(summary = "Voca 학습한 단어 개수 조회", description = "학습한 단어 개수를 가져온다.")
    @GetMapping("/voca-learn/count")
    public ResponseEntity<Integer> countByUserIdAndLearnYn(HttpServletRequest request){
        return ResponseEntity.ok(vocaLearnService.countByUserIdAndLearnYn(request));
    }
}

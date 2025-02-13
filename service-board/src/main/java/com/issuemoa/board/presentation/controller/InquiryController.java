package com.issuemoa.board.presentation.controller;

import com.issuemoa.board.application.InquiryService;
import com.issuemoa.board.presentation.dto.InquirySaveRequest;
import com.issuemoa.board.presentation.dto.InquirySaveResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Inquiry", description = "고객문의 API")
@RequiredArgsConstructor
@RestController
public class InquiryController {
    private final InquiryService inquiryService;

    @Operation(summary = "고객문의 등록")
    @PostMapping("/inquiry")
    public ResponseEntity<InquirySaveResponse> save(@RequestBody InquirySaveRequest inquirySaveRequest) {
        return ResponseEntity.ok(inquiryService.save(inquirySaveRequest));
    }
}

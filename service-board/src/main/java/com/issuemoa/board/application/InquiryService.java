package com.issuemoa.board.application;

import com.issuemoa.board.infrastructure.kafka.InquiryProducer;
import com.issuemoa.board.domain.inquiry.InquiryRepository;
import com.issuemoa.board.presentation.dto.InquirySaveRequest;
import com.issuemoa.board.presentation.dto.InquirySaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryProducer inquiryProducer;

    public InquirySaveResponse save(InquirySaveRequest inquirySaveRequest) {
        InquirySaveResponse result = InquirySaveResponse.toDto(inquiryRepository.save(inquirySaveRequest.toEntity()));

        // 고객 문의 등록 시 카프카 프로듀서(Producer) 처리
        if (result.id() > 0) {
            inquiryProducer.sendMessage(inquirySaveRequest);
        }

        return result;
    }
}

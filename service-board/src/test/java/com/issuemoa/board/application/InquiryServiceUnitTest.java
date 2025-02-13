package com.issuemoa.board.application;

import com.issuemoa.board.domain.inquiry.Inquiry;
import com.issuemoa.board.domain.inquiry.InquiryRepository;
import com.issuemoa.board.infrastructure.kafka.InquiryProducer;
import com.issuemoa.board.presentation.dto.InquirySaveRequest;
import com.issuemoa.board.presentation.dto.InquirySaveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InquiryServiceUnitTest {
    @Mock
    private InquiryRepository inquiryRepository;

    @Mock
    private InquiryProducer inquiryProducer;

    @InjectMocks
    private InquiryService inquiryService;

    @Test
    @DisplayName("고객문의 등록")
    void save() {
        // given
        Inquiry savedInquiry = Inquiry.builder()
                .id(1L)
                .name("고객문의 제목")
                .email("conf312@naver.com")
                .type("ETC")
                .contents("내용")
                .build();

        when(inquiryRepository.save(any())).thenReturn(savedInquiry);

        // when
        InquirySaveResponse response = inquiryService.save(new InquirySaveRequest(
            savedInquiry.getName(),
            savedInquiry.getEmail(),
            savedInquiry.getType(),
            savedInquiry.getContents()
        ));

        // then
        assertEquals(1L, response.id());
        assertEquals("고객문의 제목", response.name());
        assertEquals("conf312@naver.com", response.email());
        assertEquals("ETC", response.type());
        assertEquals("내용", response.contents());
    }









}

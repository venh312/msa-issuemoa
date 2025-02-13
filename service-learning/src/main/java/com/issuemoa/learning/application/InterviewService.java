package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.interview.Interview;
import com.issuemoa.learning.domain.interview.InterviewRepository;
import com.issuemoa.learning.presentation.dto.InterviewRequest;
import com.issuemoa.learning.presentation.dto.InterviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;

    @Cacheable(value = "interview", key = "#category", cacheManager = "contentCacheManager", unless = "#result == null")
    public HashMap<String, Object> findAll(String category){
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("list", interviewRepository.findByCategoryOrderById(category, "ASC"));

        return resultMap;
    }

    @Transactional
    public InterviewResponse update(InterviewRequest request) {
        Interview interview = interviewRepository.findById(request.id())
                                        .orElseThrow(() -> new IllegalArgumentException("[Interview] Not Found ::" + request.id()));

        interview.update(request.category(), request.question(), request.answer(), request.modifyId());

        return new InterviewResponse(interview.getId(), interview.getCategory(), interview.getQuestion(), interview.getAnswer());
    }
}

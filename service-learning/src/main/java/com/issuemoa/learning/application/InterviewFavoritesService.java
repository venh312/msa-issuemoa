package com.issuemoa.learning.application;

import com.issuemoa.common.jwt.TokenProvider;
import com.issuemoa.learning.domain.interview.favorites.InterviewFavorites;
import com.issuemoa.learning.domain.interview.favorites.InterviewFavoritesRepository;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesRequest;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InterviewFavoritesService {
    private final InterviewFavoritesRepository interviewFavoritesRepository;
    private final TokenProvider tokenProvider;

    public Map<String, Object> findByRegisterId(HttpServletRequest request){
        Long userId = tokenProvider.getUserId(request);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", interviewFavoritesRepository.findUserInterviewFavorites(userId).stream().map(InterviewFavoritesResponse::toDto));

        return resultMap;
    }

    public List<Long> findInterviewFavoritesIdByRegisterId(HttpServletRequest request){
        Long userId = tokenProvider.getUserId(request);
        return interviewFavoritesRepository.findInterviewFavoritesIdByRegisterId(userId);
    }

    @Transactional
    public Long save(HttpServletRequest request, InterviewFavoritesRequest interviewFavoritesRequest) {
        Long userId = tokenProvider.getUserId(request);
        Optional<InterviewFavorites> interviewFavorites = interviewFavoritesRepository.findByInterviewIdAndRegisterId(interviewFavoritesRequest.interviewId(), userId);

        if (interviewFavorites.isPresent()) {
            interviewFavorites.get().updateUseYn(interviewFavoritesRequest.useYn());
        } else {
            interviewFavoritesRepository.save(interviewFavoritesRequest.toEntity(userId));
        }

        return interviewFavoritesRequest.interviewId();
    }

    @Transactional
    public void deleteByIdAndRegisterId(HttpServletRequest request, Long id) {
        Long registerId = tokenProvider.getUserId(request);
        interviewFavoritesRepository.deleteByIdAndRegisterId(id, registerId);
    }
}

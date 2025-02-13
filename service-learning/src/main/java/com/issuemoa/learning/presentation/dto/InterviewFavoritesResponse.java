package com.issuemoa.learning.presentation.dto;

import com.issuemoa.learning.domain.interview.favorites.InterviewFavorites;

public record InterviewFavoritesResponse(
        Long id,
        String useYn,
        Long interviewId,
        String category,
        String question,
        String answer
) {

    public static InterviewFavoritesResponse toDto(InterviewFavorites interviewFavorites) {
        return new InterviewFavoritesResponse(
                        interviewFavorites.getId(),
                        interviewFavorites.getUseYn(),
                        interviewFavorites.getInterview().getId(),
                        interviewFavorites.getInterview().getCategory(),
                        interviewFavorites.getInterview().getQuestion(),
                        interviewFavorites.getInterview().getAnswer());
    }
}

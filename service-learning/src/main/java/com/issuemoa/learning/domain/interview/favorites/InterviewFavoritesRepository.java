package com.issuemoa.learning.domain.interview.favorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface InterviewFavoritesRepository extends JpaRepository<InterviewFavorites, Long> {
    @Query(value = "SELECT f FROM interview_favorites f JOIN FETCH f.interview WHERE f.registerId = :registerId AND use_yn = 'Y'")
    List<InterviewFavorites> findUserInterviewFavorites(@Param("registerId") Long registerId);

    @Query("SELECT f.interviewId FROM interview_favorites f WHERE f.registerId = :registerId AND use_yn = 'Y'")
    List<Long> findInterviewFavoritesIdByRegisterId(@Param("registerId") Long registerId);

    Optional<InterviewFavorites> findByInterviewIdAndRegisterId(Long interviewId, Long registerId);

    public void deleteByIdAndRegisterId(Long id, Long registerId);
}

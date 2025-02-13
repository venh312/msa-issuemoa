package com.issuemoa.learning.domain.voca.learn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VocaLearnRepository extends JpaRepository<VocaLearn, Long> {
    Optional<VocaLearn> findByUserIdAndVocaId(Long userId, Long vocaId);
    int countByUserIdAndLearnYn(Long userId, String learnYn);
}

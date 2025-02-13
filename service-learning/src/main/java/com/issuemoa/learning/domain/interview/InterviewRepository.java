package com.issuemoa.learning.domain.interview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long>, InterviewRepositoryCustom {
}

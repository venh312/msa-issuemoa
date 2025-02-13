package com.issuemoa.learning.domain.interview;

import com.issuemoa.learning.presentation.dto.InterviewResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterviewRepositoryImpl implements InterviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QInterview interview = QInterview.interview;
    @Override
    public List<InterviewResponse> findByCategoryOrderById(String category, String sort) {
        return jpaQueryFactory
                .select(Projections.constructor(InterviewResponse.class,
                    interview.id,
                    interview.category,
                    interview.question,
                    interview.answer
                ))
                .from(interview)
                .where(interview.category.eq(category))
                .orderBy(sort.equals("ASC") ? interview.id.asc() : interview.id.desc())
                .fetch();
    }
}

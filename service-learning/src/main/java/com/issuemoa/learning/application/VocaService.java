package com.issuemoa.learning.application;

import com.issuemoa.common.jwt.TokenProvider;
import com.issuemoa.learning.domain.voca.QVoca;
import com.issuemoa.learning.domain.voca.learn.QVocaLearn;
import com.issuemoa.learning.presentation.dto.VocaResponse;
import com.issuemoa.learning.presentation.dto.VocaRetryResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VocaService {
    private final JPAQueryFactory jpaQueryFactory;
    private final TokenProvider tokenProvider;
    private final QVoca voca = QVoca.voca;
    private final QVocaLearn vocaLearn = QVocaLearn.vocaLearn;

    public BooleanExpression eqId(Long id) {
        if (id == null) id = 0L;
        return vocaLearn.userId.eq(id);
    }

    public HashMap<String, Object> findAll(HttpServletRequest request, Integer offset, Integer limit){
        Long userId = tokenProvider.getUserId(request);

        List<VocaResponse> list = jpaQueryFactory
            .select(Projections.constructor(VocaResponse.class,
                voca.id,
                voca.word,
                voca.mean
            ))
            .from(voca)
            .where(
                JPAExpressions.selectFrom(vocaLearn)
                    .where(vocaLearn.vocaId.eq(voca.id)
                    .and(vocaLearn.learnYn.eq("Y")
                    .and(eqId(userId))))
                    .notExists()
            )
            .offset(offset)
            .limit(limit)
            .orderBy(voca.id.asc())
            .fetch();

        long totalCnt = (long) jpaQueryFactory
            .select(voca.count())
            .from(voca)
            .where(
                JPAExpressions.selectFrom(vocaLearn)
                    .where(vocaLearn.vocaId.eq(voca.id)
                    .and(vocaLearn.learnYn.eq("Y")
                    .and(eqId(userId))))
                    .notExists()
            )
            .fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / limit);
        totalPage = totalPage == 0 ? 1 : totalPage;

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("offset", offset);
        resultMap.put("limit", limit);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }

    public HashMap<String, Object> findByVocaRetry(HttpServletRequest request, Integer offset, Integer limit){
        Long userId = tokenProvider.getUserId(request);

        List<VocaRetryResponse> list = jpaQueryFactory
            .select(Projections.constructor(VocaRetryResponse.class,
                voca.id,
                voca.word,
                voca.mean,
                vocaLearn.userId,
                vocaLearn.learnYn
            ))
            .from(voca)
            .leftJoin(voca.vocaLearn, vocaLearn)  // vocaLearn을 명시적으로 지정
            .where(
                vocaLearn.userId.eq(userId)
                .and(vocaLearn.learnYn.eq("N"))
            )
            .offset(offset)
            .limit(limit)
            .orderBy(voca.id.asc())
            .fetch();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("offset", offset);
        resultMap.put("limit", limit);

        return resultMap;
    }
}

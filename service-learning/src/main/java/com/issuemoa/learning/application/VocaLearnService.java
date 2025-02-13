package com.issuemoa.learning.application;

import com.issuemoa.common.jwt.TokenProvider;
import com.issuemoa.learning.domain.voca.learn.VocaLearn;
import com.issuemoa.learning.domain.voca.learn.VocaLearnRepository;
import com.issuemoa.learning.presentation.dto.VocaLearnRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VocaLearnService {
    private final VocaLearnRepository vocaLearnRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long save(HttpServletRequest request, VocaLearnRequest vocaLearnRequest){
        Long userId = tokenProvider.getUserId(request);

        Optional<VocaLearn> findLearn = vocaLearnRepository.findByUserIdAndVocaId(userId, vocaLearnRequest.vocaId());

        // 학습 기록이 있다면 learnYn 변경
        if (findLearn.isPresent()) {
            VocaLearn learn = findLearn.get();
            learn.updateLearnYn(vocaLearnRequest.learnYn());
            return learn.getId();
        }

        return vocaLearnRepository.save(vocaLearnRequest.toEntity(userId)).getId();
    }

    public int countByUserIdAndLearnYn(HttpServletRequest request){
        Long userId = tokenProvider.getUserId(request);
        return vocaLearnRepository.countByUserIdAndLearnYn(userId, "Y");
    }
}

package com.issuemoa.board.application;

import com.issuemoa.board.domain.keyword.Keyword;
import com.issuemoa.board.domain.keyword.KeywordRepository;
import com.issuemoa.board.infrastructure.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> findTop10ByBaseDateTimeOrderByCountDesc(int minusDay) {
        LocalDateTime nowOfMinusDay = DateUtil.getNowOfMinusDay(minusDay);
        return keywordRepository.findTop10ByBaseDateTimeOrderByCountDesc(nowOfMinusDay);
    }
}

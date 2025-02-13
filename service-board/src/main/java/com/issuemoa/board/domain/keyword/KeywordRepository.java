package com.issuemoa.board.domain.keyword;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface KeywordRepository extends MongoRepository<Keyword, String> {
    List<Keyword> findTop10ByBaseDateTimeOrderByCountDesc(LocalDateTime baseDateTime);
}

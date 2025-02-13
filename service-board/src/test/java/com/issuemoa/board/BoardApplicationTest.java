package com.issuemoa.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.util.Assert;

@SpringBootTest
class BoardApplicationTest {
    @Autowired
    private MongoRepository mongoRepository;

    @Test
    void MongoBoard_조회() {
        Assert.notEmpty(mongoRepository.findAll(), "조회 결과가 없습니다.");
    }
}
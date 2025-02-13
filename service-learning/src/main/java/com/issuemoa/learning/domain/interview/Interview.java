package com.issuemoa.learning.domain.interview;

import com.issuemoa.learning.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "interview")
public class Interview extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String category;
    private String question;
    private String answer;
    private Long registerId;
    private Long modifyId;

    public void update(String category, String question, String answer, Long modifyId) {
        this.category = category;
        this.question = question;
        this.answer = answer;
        this.modifyId = modifyId;
    }
}

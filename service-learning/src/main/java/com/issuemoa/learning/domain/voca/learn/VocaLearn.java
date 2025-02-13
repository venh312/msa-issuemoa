package com.issuemoa.learning.domain.voca.learn;

import com.issuemoa.learning.domain.BaseTime;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "voca_learn")
public class VocaLearn extends BaseTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long vocaId;
    private String learnYn;
    private Long registerId;
    private Long modifyId;

    public void updateLearnYn(String learnYn) {
        this.learnYn = learnYn;
    }
}

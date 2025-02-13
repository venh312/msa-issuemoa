package com.issuemoa.learning.domain.voca;

import com.issuemoa.learning.domain.BaseTime;
import com.issuemoa.learning.domain.voca.learn.VocaLearn;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "voca")
public class Voca extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String word;
    private String mean;
    private Long registerId;
    private Long modifyId;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "vocaId", insertable = false, updatable = false)
    private VocaLearn vocaLearn;
}

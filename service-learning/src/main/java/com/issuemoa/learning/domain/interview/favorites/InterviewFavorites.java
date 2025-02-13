package com.issuemoa.learning.domain.interview.favorites;

import com.issuemoa.learning.domain.BaseTime;
import com.issuemoa.learning.domain.interview.Interview;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "interview_favorites")
public class InterviewFavorites extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "interview_id")
    private Long interviewId;
    private String useYn;
    private Long registerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Interview interview;

    public void updateUseYn(String useYn) {
        this.useYn = useYn;
    }
}

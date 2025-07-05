package com.issuemoa.learning.domain.grade;

import com.issuemoa.learning.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "grade_exp")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GradeExp extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gradeCode;
    private int standard;
    private Long registerId;
    private Long modifyId;
}

package com.issuemoa.learning.domain.grade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeExpRepository extends JpaRepository<GradeExp, Long> {
    GradeExp findTop1ByStandardLessThanEqualOrderByStandardDesc(int standard);
}

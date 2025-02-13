package com.issuemoa.learning.domain.grade;

import com.issuemoa.learning.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "grade")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Grade extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Long registerId;
    private Long modifyId;

    @Getter
    public static class Response {
        private Long id;
        private String name;
        private String code;
        private Long registerId;
        private Long modifyId;
        private String registerTime;
        private String modifyTime;

        public Response(Long id, String name, String code, Long registerId, Long modifyId, LocalDateTime registerTime, LocalDateTime modifyTime) {
            this.id = id;
            this.name = name;
            this.code = code;
            this.registerId = registerId;
            this.modifyId = modifyId;
            this.registerTime = toStringDateTime(registerTime);
            this.modifyTime = toStringDateTime(modifyTime);
        }
    }
}

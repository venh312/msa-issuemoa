package com.issuemoa.batch.domain.subsidy.detail;

import lombok.*;
import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "subsidy_detail")
@Entity
public class SubsidyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicServantDocument;      // 공무원확인구비서류
    private String requiredDocuments;          // 구비서류
    private String contactInfo;                // 문의처
    private String legalBasis;                 // 법령
    private String personalVerificationDocs;   // 본인확인필요구비서류
    private String serviceId;                  // 서비스ID
    private String serviceName;                // 서비스명
    private String servicePurpose;             // 서비스목적
    private String eligibilityCriteria;        // 선정기준
    private String responsibleAgencyName;      // 소관기관명
    private String lastModifiedDate;           // 수정일시
    private String applicationPeriod;          // 신청기한
    private String applicationMethod;          // 신청방법
    private String onlineApplicationUrl;       // 온라인신청사이트URL
    private String localRegulation;            // 자치법규
    private String receivingAgencyName;        // 접수기관명
    private String supportDetails;             // 지원내용
    private String eligibleRecipients;         // 지원대상
    private String supportType;                // 지원유형
    private String administrativeRules;        // 행정규칙
}

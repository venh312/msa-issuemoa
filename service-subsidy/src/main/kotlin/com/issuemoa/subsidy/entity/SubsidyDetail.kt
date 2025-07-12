package com.issuemoa.subsidy.entity

import javax.persistence.*

@Entity
@Table(name = "subsidy_detail")
data class SubsidyDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicServantDocument: String?,       // 공무원확인구비서류
    val requiredDocuments: String?,           // 구비서류
    val contactInfo: String?,                 // 문의처
    val legalBasis: String?,                  // 법령
    val personalVerificationDocs: String?,    // 본인확인필요구비서류
    val serviceId: String?,                   // 서비스ID
    val serviceName: String?,                 // 서비스명
    val servicePurpose: String?,              // 서비스목적
    val eligibilityCriteria: String?,         // 선정기준
    val responsibleAgencyName: String?,       // 소관기관명
    val lastModifiedDate: String?,            // 수정일시
    val applicationPeriod: String?,           // 신청기한
    val applicationMethod: String?,           // 신청방법
    val onlineApplicationUrl: String?,        // 온라인신청사이트URL
    val localRegulation: String?,             // 자치법규
    val receivingAgencyName: String?,         // 접수기관명
    val supportDetails: String?,              // 지원내용
    val eligibleRecipients: String?,          // 지원대상
    val supportType: String?,                 // 지원유형
    val administrativeRules: String?          // 행정규칙
)
package com.issuemoa.subsidy.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "subsidy")
data class Subsidy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val registrationDatetime: String? = null, // 등록일시
    val departmentName: String? = null, // 부서명
    val userType: String? = null, // 사용자구분
    val detailViewUrl: String? = null, // 상세조회URL
    val serviceId: String? = null,  // 서비스ID
    val serviceName: String? = null, // 서비스명
    val servicePurposeSummary: String? = null, // 서비스목적요약
    val serviceCategory: String? = null,  // 서비스분야
    val eligibilityCriteria: String? = null, // 선정기준
    val responsibleAgencyName: String? = null, // 소관기관명
    val responsibleAgencyType: String? = null,  // 소관기관유형
    val responsibleAgencyCode: String? = null,  // 소관기관코드
    val lastModifiedDatetime: String? = null,  // 수정일시
    val applicationPeriod: String? = null, // 신청기한
    val applicationMethod: String? = null, // 신청방법
    val contactInfo: String? = null,  // 전화문의
    val receivingAgency: String? = null, // 접수기관
    val viewCount: String? = null, // 조회수
    val supportDetails: String? = null, // 지원내용
    val eligibleRecipients: String? = null, // 지원대상
    val supportType: String? = null, // 지원유형
    val registerId: Long? = null,
    val modifyId: Long? = null,
    val registerTime: LocalDateTime? = null,
    val modifyTime: LocalDateTime? = null
)
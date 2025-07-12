package com.issuemoa.subsidy.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "subsidy")
data class Subsidy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val registrationDatetime: String? = null,
    val departmentName: String? = null,
    val userType: String? = null,
    val detailViewUrl: String? = null,
    val serviceId: String? = null,
    val serviceName: String? = null,
    val servicePurposeSummary: String? = null,
    val serviceCategory: String? = null,
    val eligibilityCriteria: String? = null,
    val responsibleAgencyName: String? = null,
    val responsibleAgencyType: String? = null,
    val responsibleAgencyCode: String? = null,
    val lastModifiedDatetime: String? = null,
    val applicationPeriod: String? = null,
    val applicationMethod: String? = null,
    val contactInfo: String? = null,
    val receivingAgency: String? = null,
    val viewCount: String? = null,
    val supportDetails: String? = null,
    val eligibleRecipients: String? = null,
    val supportType: String? = null,
    val registerId: Long? = null,
    val modifyId: Long? = null,
    val registerTime: LocalDateTime? = null,
    val modifyTime: LocalDateTime? = null
)
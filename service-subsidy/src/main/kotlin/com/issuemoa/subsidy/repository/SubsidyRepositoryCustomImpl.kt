package com.issuemoa.subsidy.repository

import com.issuemoa.subsidy.entity.QSubsidy
import com.issuemoa.subsidy.entity.QSubsidyDetail
import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SubsidyRepositoryCustomImpl(private val queryFactory: JPAQueryFactory): SubsidyRepositoryCustom {
    override fun findByServiceCategory(serviceCategoryList: List<String>, offset: Long, limit: Long): List<SubsidyResponse> {
        return queryFactory
            .select(
                Projections.constructor(
                    SubsidyResponse::class.java,
                    QSubsidy.subsidy.registrationDatetime,
                    QSubsidy.subsidy.departmentName,
                    QSubsidy.subsidy.userType,
                    QSubsidy.subsidy.detailViewUrl,
                    QSubsidy.subsidy.serviceId,
                    QSubsidy.subsidy.serviceName,
                    QSubsidy.subsidy.servicePurposeSummary,
                    QSubsidy.subsidy.serviceCategory,
                    QSubsidy.subsidy.eligibilityCriteria,
                    QSubsidy.subsidy.responsibleAgencyName,
                    QSubsidy.subsidy.responsibleAgencyType,
                    QSubsidy.subsidy.responsibleAgencyCode,
                    QSubsidy.subsidy.lastModifiedDatetime,
                    QSubsidy.subsidy.applicationPeriod,
                    QSubsidy.subsidy.applicationMethod,
                    QSubsidy.subsidy.contactInfo,
                    QSubsidy.subsidy.receivingAgency,
                    QSubsidy.subsidy.viewCount,
                    QSubsidy.subsidy.supportDetails,
                    QSubsidy.subsidy.eligibleRecipients,
                    QSubsidy.subsidy.supportType
                )
            )
            .from(QSubsidy.subsidy)
            .where(QSubsidy.subsidy.serviceCategory.`in`(serviceCategoryList))
            .orderBy(QSubsidy.subsidy.registrationDatetime.desc())
            .offset(offset)
            .limit(limit)
            .fetch()
    }

    override fun findBySupportType(supportType: String, offset: Long, limit: Long): List<SubsidyResponse> {
        return queryFactory
            .select(
                Projections.constructor(
                    SubsidyResponse::class.java,
                    QSubsidy.subsidy.registrationDatetime,
                    QSubsidy.subsidy.departmentName,
                    QSubsidy.subsidy.userType,
                    QSubsidy.subsidy.detailViewUrl,
                    QSubsidy.subsidy.serviceId,
                    QSubsidy.subsidy.serviceName,
                    QSubsidy.subsidy.servicePurposeSummary,
                    QSubsidy.subsidy.serviceCategory,
                    QSubsidy.subsidy.eligibilityCriteria,
                    QSubsidy.subsidy.responsibleAgencyName,
                    QSubsidy.subsidy.responsibleAgencyType,
                    QSubsidy.subsidy.responsibleAgencyCode,
                    QSubsidy.subsidy.lastModifiedDatetime,
                    QSubsidy.subsidy.applicationPeriod,
                    QSubsidy.subsidy.applicationMethod,
                    QSubsidy.subsidy.contactInfo,
                    QSubsidy.subsidy.receivingAgency,
                    QSubsidy.subsidy.viewCount,
                    QSubsidy.subsidy.supportDetails,
                    QSubsidy.subsidy.eligibleRecipients,
                    QSubsidy.subsidy.supportType
                )
            )
            .from(QSubsidy.subsidy)
            .where(QSubsidy.subsidy.supportType.contains(supportType))
            .orderBy(QSubsidy.subsidy.registrationDatetime.desc())
            .offset(offset)
            .limit(limit)
            .fetch()
    }

    override fun findByServiceId(serviceId: String): SubsidyDetailResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    SubsidyDetailResponse::class.java,
                    QSubsidyDetail.subsidyDetail.publicServantDocument,
                    QSubsidyDetail.subsidyDetail.requiredDocuments,
                    QSubsidyDetail.subsidyDetail.contactInfo,
                    QSubsidyDetail.subsidyDetail.legalBasis,
                    QSubsidyDetail.subsidyDetail.personalVerificationDocs,
                    QSubsidyDetail.subsidyDetail.serviceId,
                    QSubsidyDetail.subsidyDetail.serviceName,
                    QSubsidyDetail.subsidyDetail.servicePurpose,
                    QSubsidyDetail.subsidyDetail.eligibilityCriteria,
                    QSubsidyDetail.subsidyDetail.responsibleAgencyName,
                    QSubsidyDetail.subsidyDetail.lastModifiedDate,
                    QSubsidyDetail.subsidyDetail.applicationPeriod,
                    QSubsidyDetail.subsidyDetail.applicationMethod,
                    QSubsidyDetail.subsidyDetail.onlineApplicationUrl,
                    QSubsidyDetail.subsidyDetail.localRegulation,
                    QSubsidyDetail.subsidyDetail.receivingAgencyName,
                    QSubsidyDetail.subsidyDetail.supportDetails,
                    QSubsidyDetail.subsidyDetail.eligibleRecipients,
                    QSubsidyDetail.subsidyDetail.supportType,
                    QSubsidyDetail.subsidyDetail.administrativeRules
                )
            )
            .from(QSubsidyDetail.subsidyDetail)
            .where(QSubsidyDetail.subsidyDetail.serviceId.eq(serviceId))
            .fetchOne()
    }
}
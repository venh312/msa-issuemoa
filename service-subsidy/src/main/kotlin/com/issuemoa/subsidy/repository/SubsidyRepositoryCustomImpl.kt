package com.issuemoa.subsidy.repository

import com.issuemoa.subsidy.entity.QSubsidy.subsidy
import com.issuemoa.subsidy.entity.QSubsidyDetail.subsidyDetail
import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SubsidyRepositoryCustomImpl(private val queryFactory: JPAQueryFactory): SubsidyRepositoryCustom {
    override fun findSubsidyByWhere(offset: Long, limit: Long, eligibleRecipients: String, serviceCategoryList: List<String>, supportType: String): List<SubsidyResponse> {
        val predicate: BooleanExpression? = null
            .andIf(eligibleRecipients.isNotBlank()) {
                subsidy.eligibleRecipients.contains(eligibleRecipients)
            }
            .andIf(serviceCategoryList.isNotEmpty()) {
                subsidy.serviceCategory.`in`(serviceCategoryList)
            }
            .andIf(supportType.isNotBlank()) {
                subsidy.supportType.contains(supportType)
            }

        return queryFactory
            .select(
                Projections.constructor(
                    SubsidyResponse::class.java,
                    subsidy.registrationDatetime,
                    subsidy.departmentName,
                    subsidy.userType,
                    subsidy.detailViewUrl,
                    subsidy.serviceId,
                    subsidy.serviceName,
                    subsidy.servicePurposeSummary,
                    subsidy.serviceCategory,
                    subsidy.eligibilityCriteria,
                    subsidy.responsibleAgencyName,
                    subsidy.responsibleAgencyType,
                    subsidy.responsibleAgencyCode,
                    subsidy.lastModifiedDatetime,
                    subsidy.applicationPeriod,
                    subsidy.applicationMethod,
                    subsidy.contactInfo,
                    subsidy.receivingAgency,
                    subsidy.viewCount,
                    subsidy.supportDetails,
                    subsidy.eligibleRecipients,
                    subsidy.supportType
                )
            )
            .from(subsidy)
            .where(predicate)
            .orderBy(subsidy.registrationDatetime.desc())
            .offset(offset)
            .limit(limit)
            .fetch()
    }

    override fun findByServiceId(serviceId: String): SubsidyDetailResponse? {
        return queryFactory
            .select(
                Projections.constructor(
                    SubsidyDetailResponse::class.java,
                    subsidyDetail.publicServantDocument,
                    subsidyDetail.requiredDocuments,
                    subsidyDetail.contactInfo,
                    subsidyDetail.legalBasis,
                    subsidyDetail.personalVerificationDocs,
                    subsidyDetail.serviceId,
                    subsidyDetail.serviceName,
                    subsidyDetail.servicePurpose,
                    subsidyDetail.eligibilityCriteria,
                    subsidyDetail.responsibleAgencyName,
                    subsidyDetail.lastModifiedDate,
                    subsidyDetail.applicationPeriod,
                    subsidyDetail.applicationMethod,
                    subsidyDetail.onlineApplicationUrl,
                    subsidyDetail.localRegulation,
                    subsidyDetail.receivingAgencyName,
                    subsidyDetail.supportDetails,
                    subsidyDetail.eligibleRecipients,
                    subsidyDetail.supportType,
                    subsidyDetail.administrativeRules
                )
            )
            .from(subsidyDetail)
            .where(subsidyDetail.serviceId.eq(serviceId))
            .fetchOne()
    }
}
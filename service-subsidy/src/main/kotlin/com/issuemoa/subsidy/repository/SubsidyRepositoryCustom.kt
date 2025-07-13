package com.issuemoa.subsidy.repository

import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse

interface SubsidyRepositoryCustom {
    fun com.querydsl.core.types.dsl.BooleanExpression?.andIf(
        condition: Boolean,
        expressionSupplier: () -> com.querydsl.core.types.dsl.BooleanExpression
    ): com.querydsl.core.types.dsl.BooleanExpression? {
        return if (condition) {
            this?.and(expressionSupplier()) ?: expressionSupplier()
        } else {
            this
        }
    }

    fun findSubsidyByWhere(offset: Long, limit: Long, eligibleRecipients: String, serviceCategoryList: List<String>, supportType: String): List<SubsidyResponse>
    fun findByServiceId(serviceId: String): SubsidyDetailResponse?
}

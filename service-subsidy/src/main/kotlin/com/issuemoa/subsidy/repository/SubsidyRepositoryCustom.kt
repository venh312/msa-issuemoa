package com.issuemoa.subsidy.repository

import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse
import com.querydsl.core.types.dsl.BooleanExpression

interface SubsidyRepositoryCustom {
    fun com.querydsl.core.types.dsl.BooleanExpression?.andIf(
        condition: Boolean,
        expressionSupplier: () -> BooleanExpression?
    ): com.querydsl.core.types.dsl.BooleanExpression? {
        return if (condition) {
            this?.and(expressionSupplier()) ?: expressionSupplier()
        } else {
            this
        }
    }

    fun findSubsidyByWhere(offset: Long, limit: Long, eligibleRecipients: List<String>, serviceCategoryList: List<String>, supportType: List<String>): List<SubsidyResponse>
    fun findByServiceId(serviceId: String): SubsidyDetailResponse?
}

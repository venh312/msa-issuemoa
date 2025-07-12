package com.issuemoa.subsidy.repository

import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse

interface SubsidyRepositoryCustom {
    fun findByServiceCategory(serviceCategoryList: List<String>, offset: Long, limit: Long): List<SubsidyResponse>
    fun findBySupportType(supportType: String, offset: Long, limit: Long): List<SubsidyResponse>
    fun findByServiceId(serviceId: String): SubsidyDetailResponse?
}

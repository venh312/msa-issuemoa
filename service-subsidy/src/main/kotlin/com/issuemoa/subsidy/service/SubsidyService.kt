package com.issuemoa.subsidy.service

import com.issuemoa.subsidy.repository.SubsidyRepository
import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse
import org.springframework.stereotype.Service

@Service
class SubsidyService(private val subsidyRepository: SubsidyRepository) {
    fun findByServiceCategoryList(serviceCategoryList: List<String>, offset: Long, limit: Long): List<SubsidyResponse> {
        return subsidyRepository.findByServiceCategory(serviceCategoryList, offset, limit);
    }

    fun findBySupportType(supportType: String, offset: Long, limit: Long): List<SubsidyResponse> {
        return subsidyRepository.findBySupportType(supportType, offset, limit);
    }

    fun findByServiceId(serviceId: String): SubsidyDetailResponse? {
        return subsidyRepository.findByServiceId(serviceId);
    }
}
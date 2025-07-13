package com.issuemoa.subsidy.service

import com.issuemoa.subsidy.repository.SubsidyRepository
import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse
import org.springframework.stereotype.Service

@Service
class SubsidyService(private val subsidyRepository: SubsidyRepository) {
    fun findSubsidyByWhere(offset: Long, limit: Long, eligibleRecipients: String, serviceCategoryList: List<String>, supportType: String): List<SubsidyResponse> {
        return subsidyRepository.findSubsidyByWhere(offset, limit, eligibleRecipients, serviceCategoryList, supportType);
    }

    fun findByServiceId(serviceId: String): SubsidyDetailResponse? {
        return subsidyRepository.findByServiceId(serviceId);
    }
}
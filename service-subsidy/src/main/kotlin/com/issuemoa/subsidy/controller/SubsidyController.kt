package com.issuemoa.subsidy.controller

import com.issuemoa.subsidy.response.SubsidyDetailResponse
import com.issuemoa.subsidy.response.SubsidyResponse
import com.issuemoa.subsidy.service.SubsidyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Subsidy", description = "행안부 보조금24 API")
@RestController
class SubsidyController(private val subsidyService: SubsidyService) {
    @Operation(
        summary = "행안부 보조금24 조회",
        description = "서비스 카테고리를 입력 받아 해당 지역의 행안부 보조금24 목록을 반환합니다.")
    @GetMapping("/subsidy/{offset}/{limit}/{eligibleRecipients}")
    fun findSubsidyByWhere(
        @Parameter(description = "페이지 번호 0부터 1씩 증가", example = "0") @PathVariable offset: Long,
        @Parameter(description = "페이지 당 개수", example = "30") @PathVariable limit: Long,
        @Parameter(description = "[아동|청년|임산부|노인]") @RequestParam(required = false, defaultValue = "") eligibleRecipients: String,
        @Parameter(description = "[주거·자립|고용·창업]") @RequestParam(required = false, defaultValue = "") serviceCategoryList: List<String>,
        @Parameter(description = "[현금|이용권]") @RequestParam(required = false, defaultValue = "") supportType: String
    ): List<SubsidyResponse> {
        return subsidyService.findSubsidyByWhere(offset, limit, eligibleRecipients, serviceCategoryList, supportType)
    }

    @Operation(
        summary = "행안부 보조금24 상세 정보 조회",
        description = "서비스 ID를 입력 받아 해당 지역의 행안부 보조금24 상세정보를 반환합니다.")
    @GetMapping("/subsidy/detail/{serviceId}")
    fun findByServiceId(
        @Parameter(description = "654000000014", example = "654000000014")
        @PathVariable serviceId: String
    ): SubsidyDetailResponse? {
        return subsidyService.findByServiceId(serviceId)
    }
}
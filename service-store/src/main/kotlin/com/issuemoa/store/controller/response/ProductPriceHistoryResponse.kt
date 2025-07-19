package com.issuemoa.store.controller.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "상품 가격 이력 조회 응답")
data class ProductPriceHistoryResponse(
    @field:Schema(description = "상품 조사일 (yyyyMMdd)")
    val inspectDay: String,
    @field:Schema(description = "이름")
    val name: String,
    @field:Schema(description = "가격")
    val price: Int,
    @field:Schema(description = "상품 ID")
    val goodsId: Long,
) {
}
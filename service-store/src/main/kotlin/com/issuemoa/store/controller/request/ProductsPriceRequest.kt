package com.issuemoa.store.controller.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "상품 가격 이력 조회 요청")
data class ProductsPriceRequest(
    @field:Schema(description = "조회 시작일 (yyyyMMdd)", example = "20241201")
    val startDt: String,
    @field:Schema(description = "조회 종료일 (yyyyMMdd)", example = "20250704")
    val endDt: String,
    @field:Schema(description = "팔도 왕뚜껑(110g) [상품 ID]", example = "1000")
    val goodsId: Long,
    @field:Schema(description = "이마트 죽전점 [매장 ID]", example = "695")
    val entpId: Long
) {
}
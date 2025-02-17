package com.issuemoa.store.dto.response

data class ProductsResponse(
    val entpId: Long,
    val goodsId: Long,
    val name: String,
    val detailMean: String? = null,
    val price: Int,
    val plusOneYn: String? = null,
    val dcYn: String? = null,
    val dcStartDay: Int? = null,
    val dcEndDay: Int? = null,
    val inspectDay: String,
) {
}
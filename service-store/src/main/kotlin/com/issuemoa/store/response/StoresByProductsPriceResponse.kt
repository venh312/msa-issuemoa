package com.issuemoa.store.dto.response

data class StoresByProductsPriceResponse(
    val goodsId: Long,
    val price: Int,
    val entpId: Long = 0,
    val name: String,
    val tel: String? = null,
    val postNo: Int? = null,
    val addr: String,
    val addrDetail: String? = null
) {
}
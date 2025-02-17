package com.issuemoa.store.dto.response

data class StoresResponse(
   val entpId: Long = 0,
   val name: String,
   val tel: String? = null,
   val postNo: Int? = null,
   val addr: String,
   val addrDetail: String? = null
) {
}
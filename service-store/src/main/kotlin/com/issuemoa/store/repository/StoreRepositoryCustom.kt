package com.issuemoa.store.repository

import com.issuemoa.store.dto.response.ProductsResponse
import com.issuemoa.store.dto.response.ProductsUnitResponse
import com.issuemoa.store.dto.response.StoresByProductsPriceResponse
import com.issuemoa.store.dto.response.StoresResponse

interface StoreRepositoryCustom {
    fun findStores(addr: String): List<StoresResponse>
    fun findProducts(entpId: Long): List<ProductsResponse>
    fun findProductsUnit(): List<ProductsUnitResponse>
    fun findStoresByAddrAndGoodsId(addr: String, goodsId: Long): List<StoresByProductsPriceResponse>
}

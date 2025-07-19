package com.issuemoa.store.repository

import com.issuemoa.store.controller.request.ProductsPriceRequest
import com.issuemoa.store.controller.response.ProductsResponse
import com.issuemoa.store.controller.response.ProductsUnitResponse
import com.issuemoa.store.controller.response.StoresByProductsPriceResponse
import com.issuemoa.store.controller.response.StoresResponse
import com.issuemoa.store.controller.response.ProductPriceHistoryResponse

interface StoreRepositoryCustom {
    fun findStoresByAddr(addr: String): List<StoresResponse>
    fun findStoresByEntpId(entpId: Long): StoresResponse?
    fun findProducts(entpId: Long): List<ProductsResponse>
    fun findProductsUnit(): List<ProductsUnitResponse>
    fun findStoresByAddrAndGoodsId(addr: String, goodsId: Long): List<StoresByProductsPriceResponse>
    fun findProductPriceHistoryBySearchDtAndGoodsId(request: ProductsPriceRequest): List<ProductPriceHistoryResponse>
}

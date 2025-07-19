package com.issuemoa.store.service

import com.issuemoa.store.controller.request.ProductsPriceRequest
import com.issuemoa.store.controller.response.ProductPriceHistoryResponse
import com.issuemoa.store.controller.response.ProductsResponse
import com.issuemoa.store.controller.response.ProductsUnitResponse
import com.issuemoa.store.controller.response.StoresByProductsPriceResponse
import com.issuemoa.store.controller.response.StoresResponse
import com.issuemoa.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository) {

    fun findStoresByAddr(addr: String): List<StoresResponse> {
        return storeRepository.findStoresByAddr(addr)
    }

    fun findStoresByEntpId(entpId: Long): StoresResponse? {
        return storeRepository.findStoresByEntpId(entpId)
    }

    fun findProducts(entpId: Long): List<ProductsResponse> {
        return storeRepository.findProducts(entpId)
    }

    fun findProductsUnit(): List<ProductsUnitResponse> {
        return storeRepository.findProductsUnit()
    }

    fun findStoresByAddrAndGoodsId(addr: String, goodsId: Long): List<StoresByProductsPriceResponse> {
        return storeRepository.findStoresByAddrAndGoodsId(addr, goodsId)
    }

    fun findProductPriceHistoryBySearchDtAndGoodsId(request: ProductsPriceRequest): List<ProductPriceHistoryResponse> {
        return storeRepository.findProductPriceHistoryBySearchDtAndGoodsId(request)
    }
}
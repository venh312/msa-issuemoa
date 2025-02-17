package com.issuemoa.store.service

import com.issuemoa.store.dto.response.ProductsResponse
import com.issuemoa.store.dto.response.ProductsUnitResponse
import com.issuemoa.store.dto.response.StoresByProductsPriceResponse
import com.issuemoa.store.dto.response.StoresResponse
import com.issuemoa.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository) {

    fun findStores(addr: String): List<StoresResponse> {
        return storeRepository.findStores(addr)
    }

    fun findProducts(entpId: Long): List<ProductsResponse> {
        return storeRepository.findProducts(entpId)
    }

    fun findProductsUnit(): List<ProductsUnitResponse> {
        return storeRepository.findProductsUnit();
    }

    fun findStoresByAddrAndGoodsId(addr: String, goodsId: Long): List<StoresByProductsPriceResponse> {
        return storeRepository.findStoresByAddrAndGoodsId(addr, goodsId)
    }

}
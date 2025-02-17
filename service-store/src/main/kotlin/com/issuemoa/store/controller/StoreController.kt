package com.issuemoa.store.controller

import com.issuemoa.store.dto.response.ProductsResponse
import com.issuemoa.store.dto.response.ProductsUnitResponse
import com.issuemoa.store.dto.response.StoresByProductsPriceResponse
import com.issuemoa.store.dto.response.StoresResponse
import com.issuemoa.store.service.StoreService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Stores", description = "마트/백화점 API")
@RestController
class StoreController(private val storeService: StoreService) {

    @Operation(
        summary = "마트/백화점 조회",
        description = "주소(addr)를 입력 받아 해당 지역의 마트/백화점 목록을 반환합니다.")
    @GetMapping("/stores/{addr}")
    fun findStores(
        @Parameter(description = "서대문구, 강남구", example = "서대문구")
        @PathVariable addr: String
    ): List<StoresResponse> {
        return storeService.findStores(addr)
    }

    @Operation(
        summary = "마트/백화점 & 상품 가격 조회",
        description = "주소(addr)와 상품ID(goodsId)를 입력 받아 상품 가격을 포함한 마트/백화점 목록을 반환합니다.")
    @GetMapping("/stores/{addr}/{goodsId}")
    fun findStoresByAddrAndGoodsId(
        @Parameter(description = "서대문구, 강남구", example = "서대문구")
        @PathVariable addr: String,
        @Parameter(description = "상품 ID (224: 서울우유 흰우유(1L))", example = "224")
        @PathVariable goodsId: Long
    ): List<StoresByProductsPriceResponse> {
        return storeService.findStoresByAddrAndGoodsId(addr, goodsId)
    }

    @Operation(
        summary = "상품 목록 조회 (조건: 마트/백화점 ID)",
        description = "마트/백화점ID(entpId)를 입력 받아 ID에 해당하는 상품 목록을 반환합니다.")
    @GetMapping("/stores/products/{entpId}")
    fun findProducts(
        @Parameter(description = "마트/백화점 ID (786: 현대백화점 신촌점)", example = "786")
        @PathVariable entpId: Long
    ): List<ProductsResponse> {
        return storeService.findProducts(entpId)
    }

    @Operation(
        summary = "상품 단위 목록 조회",
        description = "상품 단위로 목록을 반환합니다.")
    @GetMapping("/stores/products-unit")
    fun findProductsUnit(): List<ProductsUnitResponse> {
        return storeService.findProductsUnit();
    }
}
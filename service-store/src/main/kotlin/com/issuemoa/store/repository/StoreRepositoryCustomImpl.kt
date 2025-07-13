package com.issuemoa.store.repository

import com.issuemoa.store.dto.response.ProductsResponse
import com.issuemoa.store.dto.response.ProductsUnitResponse
import com.issuemoa.store.dto.response.StoresByProductsPriceResponse
import com.issuemoa.store.dto.response.StoresResponse
import com.issuemoa.store.entity.QProducts.products
import com.issuemoa.store.entity.QStore.store
import com.issuemoa.store.entity.QProductsPrice.productsPrice
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class StoreRepositoryCustomImpl(private val queryFactory: JPAQueryFactory): StoreRepositoryCustom {
    override fun findStoresByAddr(addr: String): List<StoresResponse> {
        return queryFactory
            .select(
                Projections.constructor(StoresResponse::class.java,
                    store.entpId,
                    store.name,
                    store.tel,
                    store.postNo,
                    store.addr,
                    store.addrDetail
                )
            )
            .from(store)
            .where(
                JPAExpressions
                    .selectOne()
                    .from(productsPrice)
                    .where(productsPrice.entpId.eq(store.entpId))
                    .exists()
                .and(store.addr.contains(addr))
            )
            .fetch()
    }

    override fun findStoresByEntpId(entpId: Long): StoresResponse? {
        return queryFactory
            .select(
                Projections.constructor(StoresResponse::class.java,
                    store.entpId,
                    store.name,
                    store.tel,
                    store.postNo,
                    store.addr,
                    store.addrDetail
                )
            )
            .from(store)
            .where(store.entpId.eq(entpId))
            .fetchOne()
    }

    override fun findProducts(entpId: Long): List<ProductsResponse> {
          return queryFactory
            .select(
                Projections.constructor(
                    ProductsResponse::class.java,
                    productsPrice.entpId,
                    products.goodsId,
                    products.name,
                    products.detailMean,
                    productsPrice.price,
                    productsPrice.plusOneYn,
                    productsPrice.dcYn,
                    productsPrice.dcStartDay,
                    productsPrice.dcEndDay,
                    productsPrice.inspectDay
                )
            )
            .from(products)
            .join(productsPrice).on(
                productsPrice.goodsId.eq(products.goodsId),
                productsPrice.inspectDay.eq(
                    JPAExpressions
                        .select(productsPrice.inspectDay.max())
                        .from(productsPrice)
                        .where(
                            productsPrice.goodsId.eq(products.goodsId),
                            productsPrice.entpId.eq(entpId)
                        )
                )
            )
            .where(productsPrice.entpId.eq(entpId))
            .orderBy(productsPrice.inspectDay.desc(), productsPrice.price.asc())
            .fetch()
    }

    override fun findProductsUnit(): List<ProductsUnitResponse> {
        return queryFactory
            .select(
                Projections.constructor(ProductsUnitResponse::class.java,
                products.goodsId,
                products.name)
            )
            .from(products)
            .orderBy(products.id.asc())
            .fetch()
    }

    override fun findStoresByAddrAndGoodsId(addr: String, goodsId: Long): List<StoresByProductsPriceResponse> {
        return queryFactory
            .select(
                Projections.constructor(StoresByProductsPriceResponse::class.java,
                    productsPrice.goodsId,
                    productsPrice.price,
                    store.entpId,
                    store.name,
                    store.tel,
                    store.postNo,
                    store.addr,
                    store.addrDetail
                )
            )
            .from(store)
            .join(productsPrice).on(store.entpId.eq(productsPrice.entpId))
            .fetchJoin()
            .where(
                productsPrice.goodsId.eq(goodsId)
                    .and(store.addr.contains(addr))
            )
            .orderBy(productsPrice.price.asc())
            .fetch()
    }
}
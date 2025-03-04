package com.issuemoa.store.entity

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Products(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val goodsId: Long = 0,
    val name: String,
    val unitDivCode: String,
    val baseCnt: Int,
    val smlclsCode: Int,
    val detailMean: String,
    val totalCnt: Int,
    val totalDivCode: String,
    val registerId: Long,
    val modifyId: Long? = null,
    val registerTime: LocalDateTime,
    val modifyTime: LocalDateTime? = null
) {
}
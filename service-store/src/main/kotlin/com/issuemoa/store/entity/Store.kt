package com.issuemoa.store.entity

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "stores")
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val entpId: Long = 0,
    val name: String,
    val typeCode: String,
    val areaCode: String,
    val detailCode: String,
    val tel: String,
    val postNo: Int? = null,
    val addr: String,
    val addrDetail: String,
    val roadAddr: String,
    val roadAddrDetail: String,
    val xCoord: Int? = null,
    val yCoord: Int? = null,
    val registerId: Long,
    val modifyId: Long? = null,
    val registerTime: LocalDateTime,
    val modifyTime: LocalDateTime? = null
)
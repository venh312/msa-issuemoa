package com.issuemoa.store.repository

import com.issuemoa.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long>, StoreRepositoryCustom {
}
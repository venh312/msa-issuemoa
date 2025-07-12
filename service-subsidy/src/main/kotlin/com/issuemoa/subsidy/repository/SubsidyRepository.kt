package com.issuemoa.subsidy.repository

import com.issuemoa.subsidy.entity.Subsidy
import org.springframework.data.jpa.repository.JpaRepository

interface SubsidyRepository : JpaRepository<Subsidy, Long>, SubsidyRepositoryCustom {
}
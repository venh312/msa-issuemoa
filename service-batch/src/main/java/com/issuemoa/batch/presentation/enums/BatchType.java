package com.issuemoa.batch.presentation.enums;

import lombok.Getter;

@Getter
public enum BatchType {
    NEWS("news"),
    YOUTUBE("youtube"),
    KEYWORD("keyword"),
    STORE("store"),
    PRODUCT("product"),
    PRODUCT_PRICE("product-price"),
    SUBSIDY("subsidy"),
    SUBSIDY_DETAIL("subsidy-detail");

    private final String value;

    BatchType(String value) {
        this.value = value;
    }
}

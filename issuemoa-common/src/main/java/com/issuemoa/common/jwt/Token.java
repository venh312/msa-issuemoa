package com.issuemoa.common.jwt;

import lombok.Getter;

@Getter
public enum Token {
    ACCESS_COOKIE_NAME("access_token"),
    REFRESH_COOKIE_NAME("refresh_token");

    private final String value;

    Token(String value) {
        this.value = value;
    }
}

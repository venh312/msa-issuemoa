package com.issuemoa.gateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value); // ✅ Spring 관리 ObjectMapper 사용
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

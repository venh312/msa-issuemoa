package com.issuemoa.gateway.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Inflow {
    private String ipAddress;
    private String userAgent;
    private String referrer;
    private String requestUrl;
    private String httpMethod;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;
}


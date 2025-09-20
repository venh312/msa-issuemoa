package com.issuemoa.gateway.filter;

import com.issuemoa.gateway.domain.Inflow;
import com.issuemoa.gateway.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class InflowLoggingFilter implements GlobalFilter, Ordered {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonUtil jsonUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        Inflow inflow = Inflow.builder()
                .ipAddress(request.getRemoteAddress().getAddress().getHostAddress())
                .userAgent(request.getHeaders().getFirst("User-Agent"))
                .referrer(request.getHeaders().getFirst("Referer"))
                .requestUrl(request.getURI().toString())
                .httpMethod(request.getMethodValue())
                .registerTime(LocalDateTime.now())
                .build();

        kafkaTemplate.send("issuemoa-inflow", jsonUtil.toJson(inflow));

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // 필터 우선순위 높게
    }
}


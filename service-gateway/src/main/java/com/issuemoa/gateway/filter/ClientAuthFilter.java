package com.issuemoa.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ClientAuthFilter extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String uri = String.valueOf(request.getURI());

            log.info("[GatewayFilter] Uri :: {}", uri);
            log.info("[GatewayFilter] Address :: {}", request.getRemoteAddress().getAddress());
            log.info("[GatewayFilter] HostName :: {}", request.getRemoteAddress().getHostName());
            log.info("[GatewayFilter] Port :: {}", request.getRemoteAddress().getPort());

            // [Swagger api-docs, OAuth2] PASS
//            if (!uri.contains("v3/api-docs") && !uri.contains("oauth2")) {
//                // Request Header 에 X-CLIENT-KEY가 존재하지 않을 때
//                if (!request.getHeaders().containsKey("X-CLIENT-KEY"))
//                    return handleUnAuthorized(exchange); // 401 Error
//
//                // Request Header 에서 X-CLIENT-KEY 문자열 받아오기
//                List<String> clientKey = request.getHeaders().get("X-CLIENT-KEY");
//                String clientKeyString = Objects.requireNonNull(clientKey).get(0);
//
//                // clientKey 검증
//                if (!clientKeyString.equals("SamQHPleQjbSKeyRvJWElcHJvamVjdCFA"))
//                    return handleUnAuthorized(exchange); // 토큰이 일치 하지 않을 때
//            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("[GatewayFilter] Response Status Code ::  {}", response.getStatusCode());
            }));
        });
    }

    private Mono<Void> handleUnAuthorized(ServerWebExchange exchange) {
        log.info("[handleUnAuthorized] :: 401 Unauthorized");
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}

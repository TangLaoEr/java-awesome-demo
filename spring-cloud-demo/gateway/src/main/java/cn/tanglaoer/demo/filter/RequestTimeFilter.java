package cn.tanglaoer.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 记录请求所花时间
 *
 * @author： tks
 * @date： 2023/3/9
 */
@Component
@Slf4j
public class RequestTimeFilter implements GatewayFilter, Ordered {
    public static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(REQUEST_TIME_BEGIN,
                System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
            if (startTime != null) {
                log.info(exchange.getRequest().getURI().getRawPath() + ":" + (System.currentTimeMillis() - startTime));
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
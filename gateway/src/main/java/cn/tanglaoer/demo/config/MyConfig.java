package cn.tanglaoer.demo.config;

import com.github.xiaoymin.knife4j.spring.gateway.configuration.Knife4jGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author： tks
 * @date： 2023/3/8
 */
@Configuration
@Slf4j
public class MyConfig {
    @Primary
    @Bean("gatewaySwaggerRoute2")
    public RouterFunction<ServerResponse> gatewaySwaggerRoute(Knife4jGatewayProperties knife4jGatewayProperties) {
        log.info("init gateway swagger resources.");
        RouterFunction<ServerResponse> build = RouterFunctions.route().GET("/swagger-resources", (request) -> {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(knife4jGatewayProperties.build());
        }).build();
        return build;
    }
}

package cn.tanglaoer.demo.config;

import cn.tanglaoer.demo.feign.UserFeignService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Http Interface 方式调用
 * @author： tks
 * @date： 2023/3/6
 */
@Configuration
public class FeignConfig {
    @Bean
    WebClient webClient() {
        return WebClient.builder().baseUrl("http://localhost:9001/")
                .build();
    }

    @Bean
    UserFeignService userFeingService(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        UserFeignService client = httpServiceProxyFactory.createClient(UserFeignService.class);
        return client;
    }
}

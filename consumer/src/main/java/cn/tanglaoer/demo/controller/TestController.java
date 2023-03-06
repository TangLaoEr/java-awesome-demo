package cn.tanglaoer.demo.controller;

import cn.tanglaoer.demo.feign.UserFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： tks
 * @date： 2023/3/6
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private UserFeignService userFeingService;

    @GetMapping("/user")
    public String test() {
        log.info("调用远程接口");

        // log.info("调用远程接口开始");
        // HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        // UserFeingService client =
        //         httpServiceProxyFactory.createClient(UserFeingService.class);
        // log.info("调用远程接口结束");
        // return client.getRandom();
        return userFeingService.getRandom();
    }
}

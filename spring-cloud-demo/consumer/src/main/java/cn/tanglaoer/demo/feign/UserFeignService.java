package cn.tanglaoer.demo.feign;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @author： tks
 * @date： 2023/3/6
 */
@HttpExchange("/demo")
public interface UserFeignService {
    @GetExchange("/random")
    String getRandom();
}

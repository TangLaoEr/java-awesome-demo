package cn.tanglaoer.demo.feign.hystrix;

import cn.tanglaoer.demo.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author： tks
 * @date： 2023/3/7
 */
@Component
@Slf4j
public class UserFeignHystrix implements UserFeign {
    @Override
    public String getRandom() {
        log.info("熔断失败");
        return "熔断啦";
    }
}

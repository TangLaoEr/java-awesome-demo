package cn.tanglaoer.demo.feign;

import cn.tanglaoer.demo.feign.hystrix.UserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author： tks
 * @date： 2023/3/7
 */
@FeignClient(value = "privoder9002", fallback = UserFeignHystrix.class)
public interface UserFeign {
    @GetMapping("/demo/random")
    public String getRandom();
}

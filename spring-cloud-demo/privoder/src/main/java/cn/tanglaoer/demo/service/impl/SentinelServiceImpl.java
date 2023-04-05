package cn.tanglaoer.demo.service.impl;

import cn.tanglaoer.demo.service.ISentinelService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/5
 */
@Service
public class SentinelServiceImpl implements ISentinelService {
    @Override
    @SentinelResource("getRount")
    public String getRount(String rount1) {
        return rount1;
    }
}

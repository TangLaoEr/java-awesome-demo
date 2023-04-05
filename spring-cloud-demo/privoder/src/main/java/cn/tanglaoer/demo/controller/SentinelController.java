package cn.tanglaoer.demo.controller;

import cn.tanglaoer.demo.service.ISentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/5
 */
@Slf4j
@RestController
public class SentinelController {

    @Autowired
    private ISentinelService sentinelService;

    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT" + Thread.currentThread().getName());
        return "testD -----";
    }

    @GetMapping("/testA")
    public String testA(){
        return "testA-----";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + "...testB ");
        return "testB   -----";
    }


    @GetMapping("/rount1")
    public String rount1(){
        sentinelService.getRount("rount1");
        return "rount1-----";
    }

    @GetMapping("/rount2")
    public String rount2(){
        sentinelService.getRount("rount2");
        return "rount2-----";
    }
}

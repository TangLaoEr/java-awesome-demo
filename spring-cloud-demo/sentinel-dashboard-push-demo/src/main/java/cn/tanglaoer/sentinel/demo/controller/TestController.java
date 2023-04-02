package cn.tanglaoer.sentinel.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/3/30
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/demo")
    public String demo() {
        return "/test/demo success";
    }
}

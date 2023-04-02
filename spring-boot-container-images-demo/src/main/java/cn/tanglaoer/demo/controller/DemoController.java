package cn.tanglaoer.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/3/26
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test")
    public String getString() {
        return "hello world success";
    }
}

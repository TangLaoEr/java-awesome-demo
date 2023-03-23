package cn.tanglaoer.demo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.tanglaoer.demo.entity.User;
import cn.tanglaoer.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@RestController
@Slf4j
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getById")
    public String getDemo(Integer id) {
        User user = userService.getById(id);
        System.out.println(user);
        return user.toString();
    }

    @GetMapping("/random")
    public String getRandom() {
        log.info("接口被调用");
        return RandomUtil.randomString(8);
    }

    @GetMapping("/send/message")
    public String sendMessage() {
        return userService.sendMessage();
    }

    @GetMapping("/send/message/tag")
    public String sendMessageTag() {
        return userService.sendMessageTag();
    }
}

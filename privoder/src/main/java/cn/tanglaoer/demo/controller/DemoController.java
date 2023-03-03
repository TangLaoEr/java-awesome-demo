package cn.tanglaoer.demo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.tanglaoer.demo.entity.User;
import cn.tanglaoer.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private IUserService userService;

    @GetMapping("/demo")
    public String getDemo(Integer id) {
        User user = userService.getById(id);
        System.out.println(user);
        return user.toString();
    }

    @GetMapping("/random")
    public String getRandom() {
        return RandomUtil.randomString(8);
    }
}

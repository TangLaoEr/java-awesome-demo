package cn.tanglaoer.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/4
 */
@RestController
@RequestMapping("/rest")
public class TestRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/demo")
    public String demo() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://privoder9002/demo/random", String.class);
        System.out.println(response);
        return "success";
    }
}

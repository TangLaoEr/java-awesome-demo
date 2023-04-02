package cn.tanglaoer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application9002 {
    public static void main(String[] args) {
        SpringApplication.run(Application9002.class, args);
    }
}

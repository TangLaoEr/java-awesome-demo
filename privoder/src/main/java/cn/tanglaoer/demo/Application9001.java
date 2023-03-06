package cn.tanglaoer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application9001 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application9001.class, args);
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(RestController.class);
        System.out.println(beansWithAnnotation.size());

        System.out.println("=========");

        Map<String, Object> annotationMap =
                context.getBeansWithAnnotation(EnableAutoConfiguration.class);
        Set<String> keys = annotationMap.keySet();
        for (String key : keys) {
            annotationMap.get(key);
        }

    }
}

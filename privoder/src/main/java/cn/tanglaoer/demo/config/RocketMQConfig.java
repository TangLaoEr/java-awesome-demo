package cn.tanglaoer.demo.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author： tks
 * @date： 2023/3/4
 */
@Configuration
public class RocketMQConfig {
    @Value("${jms.nameserver}")
    private String nameserver;

    @Value("${jms.topic}")
    private String topic;

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer());
        return rocketMQTemplate;
    }


    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer =
                new DefaultMQProducer(this.topic);
        producer.setNamesrvAddr(this.nameserver);
        return producer;
    }
}

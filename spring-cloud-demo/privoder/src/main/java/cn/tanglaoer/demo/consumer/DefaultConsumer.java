package cn.tanglaoer.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author： tks
 * @date： 2023/3/5
 */
@Component("defaultConsumer")
@RocketMQMessageListener(topic = "ddddd",
        consumerGroup = "ddddd",
        selectorExpression = "hello"
)
public class DefaultConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String s) {
        System.out.println(new Date());
        System.out.println("consumer 收到消息");
        System.out.println(s);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setConsumeThreadMax(10);
    }
}

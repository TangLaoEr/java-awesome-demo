package cn.tanglaoer.demo.service.impl;

import cn.tanglaoer.demo.entity.User;
import cn.tanglaoer.demo.mapper.UserMapper;
import cn.tanglaoer.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RocketMQTemplate mqTemplate;

    public static final String tag = "hello";

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    private DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("test");
        // 指定nameServer地址
        producer.setNamesrvAddr("localhost:9876");
        // 设置当发送失败时重试发送的次数，默认为2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限为5s，默认3s
        producer.setSendMsgTimeout(5000);

        // 开启生产者
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

    @Override
    public String sendMessage() {
        byte[] body = ("Hi" + 1).getBytes(StandardCharsets.UTF_8);
        Message message = new Message("ddddd", body);
        message.setKeys(UUID.randomUUID().toString().substring(5));
        SendResult send = null;
        try {
            // send = defaultMQProducer().send(message);
            send = mqTemplate.getProducer().send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(send);
        return send.getMsgId();
    }

    @Override
    public String sendMessageTag() {
        byte[] body = ("Hi" + 1).getBytes(StandardCharsets.UTF_8);
        Message message = new Message("ddddd", "hello",  body);
        String key = UUID.randomUUID().toString().substring(5);
        message.setKeys(key);
        System.out.println("key:" + key);
        SendResult send = null;
        try {
            // send = defaultMQProducer().send(message);
            send = mqTemplate.getProducer().send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(send);
        System.out.println(new Date());
        return send.getMsgId();
    }
}

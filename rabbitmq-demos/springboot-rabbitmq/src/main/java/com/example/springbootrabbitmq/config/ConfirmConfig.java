package com.example.springbootrabbitmq.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 配置类 发布确认 （高级内容）
 * @author： tks
 * @date： 2022/7/22
 * @version： V1.0
 */

@Configuration
public class ConfirmConfig {

    // 交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    // 队列
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    // RoutingKey
    public static final String CONFIRM_ROUTING_KEY = "key1";
    // 备份交换机
    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";
    // 备份队列
    public static final String BACKUP_QUEUE_NAME = "backup_queue";
    // 报警队列
    public static final String WARNING_QUEUE_NAME = "warning_queue";

    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME)
                .durable(true)
                .withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME).build();
    }


    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    /**
     * 这里用扇出交换机
     * @return
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmExchange") DirectExchange confirmExchange,
                                        @Qualifier("confirmQueue") Queue confirmQueue) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }

    /**
     * 备份队列
     */
    @Bean
    public Binding backupQueueBindingBackupExchange(@Qualifier("backupExchange") FanoutExchange backupExchange,
                                        @Qualifier("backupQueue") Queue backupQueue) {
        // routingkey 为空字符串
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    /**
     * 报警队列
     */
    @Bean
    public Binding warningQueueBindingBackupExchange(@Qualifier("backupExchange") FanoutExchange backupExchange,
                                                    @Qualifier("warningQueue") Queue warningQueue) {
        // routingkey 为空字符串
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }
}

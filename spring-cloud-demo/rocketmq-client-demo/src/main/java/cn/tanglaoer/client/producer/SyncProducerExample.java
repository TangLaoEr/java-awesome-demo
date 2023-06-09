/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tanglaoer.client.producer;

import org.apache.rocketmq.client.apis.*;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class SyncProducerExample {
    // 总共有三个接口、这里用remoting端口
    public static final String END_POINTS = "localhost:8081";

    private static final Logger log = LoggerFactory.getLogger(SyncProducerExample.class);

    private SyncProducerExample() {
    }

    public static void main(String[] args) throws ClientException, IOException, InterruptedException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();

        // Credential provider is optional for client configuration.
        String accessKey = "yourAccessKey";
        String secretKey = "yourSecretKey";
        SessionCredentialsProvider sessionCredentialsProvider =
            new StaticSessionCredentialsProvider(accessKey, secretKey);

        // String endpoints = "foobar.com:8080";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
            .setEndpoints(END_POINTS)
            .setRequestTimeout(Duration.ofSeconds(300))
            // .setCredentialProvider(sessionCredentialsProvider)
            .build();
        String topic = "yourTopic";
        // In most case, you don't need to create too many producers, singleton pattern is recommended.
        final Producer producer = provider.newProducerBuilder()
            .setClientConfiguration(clientConfiguration)
            // Set the topic name(s), which is optional but recommended. It makes producer could prefetch the topic
            // route before message publishing.
            .setTopics(topic)
            // May throw {@link ClientException} if the producer is not initialized.
            .build();
        // Define your message body.
        byte[] body = "This is a normal message for Apache RocketMQ".getBytes(StandardCharsets.UTF_8);
        String tag = "yourMessageTagA";
        final Message message = provider.newMessageBuilder()
            // Set topic for the current message.
            .setTopic(topic)
            // Message secondary classifier of message besides topic.
            .setTag(tag)
            // Key(s) of the message, another way to mark message besides message id.
            .setKeys("keys")
            .setBody(body)
            .build();
        // Set individual thread pool for send callback.
        // final CompletableFuture<SendReceipt> future = producer.sendAsync(message);
        // ExecutorService sendCallbackExecutor = Executors.newCachedThreadPool();
        // future.whenCompleteAsync((sendReceipt, throwable) -> {
        //     if (null != throwable) {
        //         log.error("Failed to send message", throwable);
        //         // Return early.
        //         return;
        //     }
        //     log.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
        // }, sendCallbackExecutor);
        // Block to avoid exist of background threads.
        for (int i = 0; i < 100; i++) {
            SendReceipt send = producer.send(message);
            log.info(String.valueOf(send.getMessageId()));
        }
        Thread.sleep(Long.MAX_VALUE);
        // Close the producer when you don't need it anymore.
        producer.close();
    }
}

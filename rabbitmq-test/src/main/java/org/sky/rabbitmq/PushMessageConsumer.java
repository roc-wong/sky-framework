package org.sky.rabbitmq;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import hbec.app.stock.rabbitmq.module.Ack;
import hbec.app.stock.rabbitmq.module.ProcessResult;
import hbec.app.stock.rabbitmq.module.Reject;
import hbec.app.stock.rabbitmq.utils.ConsumerTemplate;
import org.sky.rabbitmq.ratelimiter.RateLimiter;
import org.sky.rabbitmq.ratelimiter.RedisRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author roc
 * @date 2017/12/08
 */
public class PushMessageConsumer {

    private static Logger logger = LoggerFactory.getLogger(PushMessageConsumer.class);

    PushService pushService = new PushService();

    Gson gson = new Gson();

    RateLimiter redisRateLimiter = new RedisRateLimiter("rateLimiterTest", 50, 5, TimeUnit.SECONDS);

    public PushMessageConsumer(int consumerNum, int prefetchCount) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Address address = new Address("10.0.29.29", 5672);
        Connection connection = factory.newConnection(new Address[]{address});

        final Channel channel = connection.createChannel();
        String queueName = "rate_limter_queue_test";
        channel.queueDeclare(queueName, false, false, true, null);
        String exchange = "rate_limter_exchange_test";
        channel.queueBind(queueName, exchange, "");
        channel.close();

        for (int i = 0; i < consumerNum; i++) {
            Channel consumerChannel = connection.createChannel();
            consumerChannel.basicQos(prefetchCount);
            consumerChannel.basicConsume(queueName, false, new DefaultConsumer(consumerChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    testHandleDelivery(getChannel(), envelope, body);
                }
            });
        }

    }

    public void testHandleDelivery(Channel channel, final Envelope envelope, final byte[] body) throws IOException {

        ConsumerTemplate.newInstance(channel).handleDelivery(new ConsumerTemplate.ConsumerProcess() {
            @Override
            public ProcessResult execute() {
                ProcessResult processResult;
                try {
                    String byteBody = new String(body, "UTF-8");
                    Message message = gson.fromJson(byteBody, Message.class);

                    redisRateLimiter.acquire(1);

                    pushService.push(message);

                    processResult = Ack.newBuilder().setDeliveryTag(envelope.getDeliveryTag()).setMultiple(false).build();

                } catch (Exception e) {
                    return Reject.newBuilder().setDeliveryTag(envelope.getDeliveryTag())
                            .setRequeue(true).build();
                }
                return processResult;
            }
        });

    }
}
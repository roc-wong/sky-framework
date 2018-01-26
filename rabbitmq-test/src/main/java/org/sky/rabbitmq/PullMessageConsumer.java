package org.sky.rabbitmq;

import com.google.gson.Gson;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;
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
public class PullMessageConsumer {

    private static Logger logger = LoggerFactory.getLogger(PullMessageConsumer.class);

    PushService pushService = new PushService();

    Gson gson = new Gson();

    RateLimiter redisRateLimiter = new RedisRateLimiter("rateLimiterTest", 50, 5, TimeUnit.SECONDS);

    public PullMessageConsumer(int consumerNum, int prefetchCount) throws IOException {

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

        Channel consumerChannel = connection.createChannel();
        consumerChannel.basicQos(prefetchCount);
        while (true) {

            GetResponse getResponse = consumerChannel.basicGet(queueName, false);
            if(getResponse == null){

            }

            boolean acquired = redisRateLimiter.tryAcquire(1);
            if (acquired) {

            } else {

            }
        }
    }

    public void testHandleDelivery(Channel channel, final Envelope envelope, final byte[] body) throws IOException {

        ConsumerTemplate.newInstance(channel).handleDelivery(new ConsumerTemplate.ConsumerProcess() {
            @Override
            public ProcessResult execute() {
                Message message = null;
                boolean result = false;
                try {
                    String byteBody = new String(body, "UTF-8");
                    message = gson.fromJson(byteBody, Message.class);

                    pushService.push(message);
                    if (result) {
                        return Ack.newBuilder().setDeliveryTag(envelope.getDeliveryTag()).setMultiple(false).build();
                    } else {
                        return Reject.newBuilder().setDeliveryTag(envelope.getDeliveryTag())
                                .setRequeue(true).build();
                    }
                } catch (Exception e) {
                    return Reject.newBuilder().setDeliveryTag(envelope.getDeliveryTag())
                            .setRequeue(true).build();
                } finally {
                    logger.info("{} message {} elapsed {}", result ? "ack" : "reject", message.getContent(), System.currentTimeMillis() - message.getDate());
                }
            }
        });

    }
}
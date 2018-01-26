package org.sky.rabbitmq;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

/**
 * @author roc
 * @date 2017/12/08
 */
public class ConsumerTest {

    private static Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

    public static void main(String[] args) {
        testHandleDelivery();
    }

    @Test
    public static void testHandleDelivery() {

        try {
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

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, final Envelope envelope, AMQP.BasicProperties properties, final byte[] body) throws IOException {

                    ConsumerTemplate.newInstance(channel).handleDelivery(new ConsumerTemplate.ConsumerProcess() {
                        @Override
                        public ProcessResult execute() {
                            String message = null;
                            try {
                                message = new String(body, "UTF-8");
                                int random = new Random().nextInt(10);
                                Thread.sleep(2000);
                                if (random % 2 == 0) {
                                    logger.info(" Ack message {}", message);
                                    return Ack.newBuilder().setDeliveryTag(envelope.getDeliveryTag()).setMultiple(false).build();
                                } else if (random % 3 == 0) {
                                    throw new RuntimeException("测试reject -> " + message);
                                } else {
                                    logger.info(" Reject message {}", message);
                                    return Reject.newBuilder().setDeliveryTag(envelope.getDeliveryTag())
                                            .setRequeue(true).build();
                                }

                            } catch (Exception e) {
                                logger.error(" Reject message {}", message);
                                return Reject.newBuilder().setDeliveryTag(envelope.getDeliveryTag())
                                        .setRequeue(true).build();
                            }
                        }
                    });
                }
            };
            channel.basicQos(100);
            channel.basicConsume(queueName, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
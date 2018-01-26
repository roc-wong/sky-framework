package org.sky.disruptor.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.sky.disruptor.NotifyEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);


    public static void main(String[] args) throws IOException {
        String queueName = "message_center_debug_queue";
        String exchangeName = "message_center_debug_exchange";

        final NotifyEventProducer producer = NotifyEventProducer.getInstance();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Address address = new Address("10.0.29.29", 5672);
        Connection connection = factory.newConnection(new Address[]{address});
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, "");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                LOGGER.info("Received message = {}", message);
                producer.send(message);
            }
        };
        channel.basicConsume(queueName, true, consumer);

    }

}

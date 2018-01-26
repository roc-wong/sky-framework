package org.sky.disruptor.rabbitmq;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Address address = new Address("10.0.29.29", 5672);
        Connection connection = factory.newConnection(new Address[]{address});

        Channel channel = connection.createChannel();
        String exchange = "message_center_debug_exchange";
        channel.exchangeDeclare(exchange, "fanout", false);

//		channel.close();
//		connection.close();

        String input;
        while (true){
            LOGGER.info("请输入消息数量：");
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            input = new BufferedReader(inputStreamReader).readLine();
            Integer number = Integer.valueOf(input);
            for (int i = 0; i < number; i++) {
                channel.basicPublish(exchange, "", null, ("hello disruptor " + i).getBytes());
            }

            if("!".equals(input)){
                break;
            }
        }
        System.exit(0);
    }

}

package org.sky.rabbitmq;

import com.google.gson.Gson;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;

public class ProducerTest {

    public static void main(String[] args) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Address address = new Address("10.0.29.29", 5672);
        Connection connection = factory.newConnection(new Address[]{address});

        Channel channel = connection.createChannel();
        String exchange = "rate_limter_exchange_test";
        channel.exchangeDeclare(exchange, "fanout", false);
        Gson gson = new Gson();
        while (true) {
            System.out.println("请输入生产的消息数量");
            Scanner scan = new Scanner(System.in);
            int messageNum = scan.nextInt();

            if(messageNum <=0){
                break;
            }

            for (int i = 1; i < messageNum; i++) {
                Message message = new Message();
                message.setContent("hello world, " + i);
                channel.basicPublish(exchange, "", null, gson.toJson(message).getBytes());
            }
        }

        channel.close();
        connection.close();

    }

}

//package org.sky.framework.test.concurrent.cyclicbarrier;
//
//import com.google.gson.Gson;
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Address;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DefaultConsumer;
//import com.rabbitmq.client.Envelope;
//import org.sky.framework.test.concurrent.cyclicbarrier.constant.RabbitMQConstant;
//
//import java.io.IOException;
//
//import static org.sky.framework.test.concurrent.cyclicbarrier.constant.RabbitMQConstant.CYCLIC_BARRIER_TEST_ROUTING_KEY;
//
//public class MessageConsumer {
//
//    public MessageConsumer(){
//        try {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setUsername("guest");
//            factory.setPassword("guest");
//            Address address = new Address("10.0.29.29", 5672);
//            Connection connection = factory.newConnection(new Address[]{address});
//
//            final Channel channel = connection.createChannel();
//            String queueName = RabbitMQConstant.CYCLIC_BARRIER_TEST_QUEUE;
//            channel.queueDeclare(queueName, false, false, true, null);
//            channel.queueBind(queueName, RabbitMQConstant.CYCLIC_BARRIER_TEST_EXCHANGE, CYCLIC_BARRIER_TEST_ROUTING_KEY);
//            channel.close();
//
//            channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    handleDelivery(getChannel(), envelope, body);
//                }
//
//                private void handleDelivery(Channel channel, Envelope envelope, byte[] body) {
//
//                    try {
//                        String byteBody = new String(body, "UTF-8");
//                        Message message = new Gson().fromJson(byteBody, Message.class);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}

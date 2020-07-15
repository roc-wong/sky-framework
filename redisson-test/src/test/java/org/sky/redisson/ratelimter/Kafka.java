package org.sky.redisson.ratelimter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author roc
 * @since 2019/7/8 15:12
 */
public class Kafka {

    private static final Logger LOGGER = LoggerFactory.getLogger(Kafka.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.29.180.87:9092,10.29.180.88:9092,10.29.180.89:9092");
        props.put("group.id", "test-1");//消费者的组id
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //订阅主题列表topic ,"nginx-access-log"
        consumer.subscribe(Arrays.asList("nginx-error-log"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                LOGGER.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());

                Iterator<Header> iterator = record.headers().iterator();
                while (iterator.hasNext()){
                    Header header = iterator.next();
                    LOGGER.info("key={}, value={}", header.key(), header.value());
                }

            }

        }
    }
}

package org.sky.framework.test.protobuf;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import hbec.commons.domain.stock.dto.MonitorMarketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class QuotationConsumer {

	private static final Logger logger = LoggerFactory.getLogger(QuotationConsumer.class);

	public static void main(String[] args)  {
		try {
			String queueName = "roc_sh_fund_queue_final";
//			String exchange = "sh_stock_exchangeV2";
//			String exchange = "sz_stock_exchangeV2";
//			String exchange = "sh_fund_exchangeV2";
			String exchange_protobuf = "sh_fund_exchangeV2";
			String exchange_json = "hu_fund_exchange";
//			String exchange = "sh_index_exchangeV2";
//			String exchange = "sz_index_exchangeV2";
//			String exchange = "sh_rebond_exchangeV2";
//			String exchange = "sz_rebond_exchangeV2";

			ConnectionFactory factory = new ConnectionFactory();
			factory.setUsername("guest");
			factory.setPassword("guest");
			Address address = new Address("10.0.30.67", 56720);
			Connection connection = factory.newConnection(new Address[] { address });

			Channel channel = connection.createChannel();
			DefaultConsumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

					byte[] message = ByteBuffer.wrap(body).array();

					QuotationMessage.QuotationPack quotationPack = null;
					try {
						quotationPack = QuotationMessage.QuotationPack.parseFrom(message);
						logger.info("quotationPack={},", quotationPack);
						Map<String, MonitorMarketDto> monitorMarketDtoMap = QuotationMessages.transform2MonitorMarket(quotationPack);
					} catch (Exception e) {
						logger.error("market deserialize error", e);
					}
				}
			};
			channel.queueDeclare(queueName, false, false, true, null);
			channel.queueBind(queueName, exchange_json, "roc_json");
			channel.queueBind(queueName, exchange_protobuf, "roc_protobuf");
			channel.basicConsume(queueName, true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}

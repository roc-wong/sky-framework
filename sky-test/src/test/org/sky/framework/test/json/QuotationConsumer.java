//package org.sky.framework.test.json;
//
//import com.google.common.base.Function;
//import com.google.common.collect.Maps;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Address;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DefaultConsumer;
//import com.rabbitmq.client.Envelope;
//import hbec.app.stock.common.enumtype.ExchangeID;
//import hbec.app.stock.common.utils.JodaDateUtil;
//import hbec.app.stock.condition.util.SimpleMarketUtils;
//import hbec.commons.domain.stock.condition.dto.SimpleMarketDto;
//import hbec.commons.domain.stock.dto.MonitorMarketDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class QuotationConsumer {
//
//	private static final Logger logger = LoggerFactory.getLogger(QuotationConsumer.class);
//
//	public static void main(String[] args)  {
//		try {
//			String queueName = "roc_stock_queue";
//			String exchange = "stock_exchange";
//
//			ConnectionFactory factory = new ConnectionFactory();
//			factory.setUsername("guest");
//			factory.setPassword("guest");
//			Address address = new Address("10.0.30.67", 56720);
//			Connection connection = factory.newConnection(new Address[] { address });
//
//			Channel channel = connection.createChannel();
//			DefaultConsumer consumer = new DefaultConsumer(channel) {
//				@Override
//				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//
//					try {
//						String str = new String(body, Charset.forName("utf-8"));
//						HashMap<String, SimpleMarketDto> sme = new Gson().fromJson(str, new TypeToken<HashMap<String, SimpleMarketDto>>() {
//						}.getType());
//						Map<String, MonitorMarketDto> monitorMarketDtoMap = Maps.newHashMap(Maps.transformValues(sme, new Function<SimpleMarketDto, MonitorMarketDto>() {
//							@Override
//							public MonitorMarketDto apply(SimpleMarketDto simpleMarketDto) {
//								MonitorMarketDto monitorMarketDto = SimpleMarketUtils.toMonitorMarketDto(simpleMarketDto);
//								logSampledData(monitorMarketDto, monitorMarketDto.getMarketSource());
//								return monitorMarketDto;
//							}
//						}));
//					} catch (Exception e) {
//						logger.error("market deserialize error", e);
//					}
//				}
//			};
//			channel.queueDeclare(queueName, false, false, true, null);
//			channel.queueBind(queueName, exchange, "");
//			channel.basicConsume(queueName, true, consumer);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//
//	private static void logSampledData(MonitorMarketDto monitorMarketDto, String marketSource) {
//		StringBuilder quotationLog = new StringBuilder();
//		quotationLog.append(JodaDateUtil.TIMESTAMP_MS_FORMATTER.print(new Date().getTime())).append("|").append(monitorMarketDto.getName()).append("|").append(monitorMarketDto.getCode())
//				.append(".").append(monitorMarketDto.getMarketSource()).append("|").append(monitorMarketDto.getOpenPrice())
//				.append("|").append(monitorMarketDto.getCurrentPrice()).append("|").append(monitorMarketDto.getPreviousPrice())
//				.append("|").append(monitorMarketDto.getChangesPercent()).append("|").append(monitorMarketDto.getUpPrice())
//				.append("/").append(monitorMarketDto.getLowPrice()).append("|").append(monitorMarketDto.getTotalNum())
//				.append("|").append(monitorMarketDto.getTotalAmt()).append("|").append(monitorMarketDto.getState())
//				.append("|").append(monitorMarketDto.getSaleFivePrice()).append("/").append(monitorMarketDto.getSaleFiveNum())
//				.append("|").append(monitorMarketDto.getSaleFourPrice()).append("/").append(monitorMarketDto.getSaleFourNum())
//				.append("|").append(monitorMarketDto.getSaleThreePrice()).append("/").append(monitorMarketDto.getSaleThreeNum())
//				.append("|").append(monitorMarketDto.getSaleTwoPrice()).append("/").append(monitorMarketDto.getSaleTwoNum())
//				.append("|").append(monitorMarketDto.getSaleOnePrice()).append("/").append(monitorMarketDto.getSaleOneNum())
//				.append("|").append(monitorMarketDto.getBuyOnePrice()).append("/").append(monitorMarketDto.getBuyOneNum())
//				.append("|").append(monitorMarketDto.getBuyTwoPrice()).append("/").append(monitorMarketDto.getBuyTwoNum())
//				.append("|").append(monitorMarketDto.getBuyThreePrice()).append("/").append(monitorMarketDto.getBuyThreeNum())
//				.append("|").append(monitorMarketDto.getBuyFourPrice()).append("/").append(monitorMarketDto.getBuyFourNum())
//				.append("|").append(monitorMarketDto.getBuyFivePrice()).append("/").append(monitorMarketDto.getBuyFiveNum())
//				.append("|").append(monitorMarketDto.getTradeDate()).append("|");
//		if (ExchangeID.SH.equalsValue(marketSource)) {
//			logger.info("securityType={}, quotation={}", marketSource, quotationLog);
//		} else {
//			logger.info("securityType={}, quotation={}", marketSource, quotationLog);
//		}
//	}
//
//
//}

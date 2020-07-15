//package org.sky.framework.test.protobuf;
//
//import com.google.common.base.Optional;
//import com.google.common.collect.Maps;
//import hbec.app.stock.common.enumtype.ExchangeID;
//import hbec.app.stock.common.utils.JodaDateUtil;
//import hbec.commons.domain.stock.dto.MonitorMarketDto;
//
//import org.apache.commons.lang3.StringUtils;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * Description: 行情转换工具
// * Created by roc on 5/31/2017.
// */
//public class QuotationMessages {
//
//
//	private static final Logger logger = LoggerFactory.getLogger(QuotationMessages.class);
//	public static final DateTimeFormatter TIMESTAMP_MS_FORMATTER = DateTimeFormat.forPattern("yyyyMMddHHmmssSSS");
//
//	public static Map<String, MonitorMarketDto> transform2MonitorMarket(QuotationMessage.QuotationPack quotationPack) {
//		//logger.info("quotation -> {}", quotationPack);
//		Map<String, MonitorMarketDto> monitorMarketDtoMap = Maps.newHashMap();
//
//		switch (quotationPack.getSecuType()) {
//			case SZ_Index:
//			case SZ_Stock:
//			case SZ_Fund:
//			case SZ_Bond:
//				monitorMarketDtoMap = transformSZMarket(quotationPack);
//				break;
//			case SH_Index:
//				monitorMarketDtoMap = transformSHIndex(quotationPack);
//				break;
//			case SH_Stock:
//			case SH_Fund:
//			case SH_Bond:
//				monitorMarketDtoMap = transformSHStockFundBond(quotationPack);
//				break;
//			case CSIndex:
//			case CSIopv:
//				logger.warn("unsupported csi market");
//			default:
//				logger.error("unknown security type, quotation={}", quotationPack);
//				break;
//		}
//		return monitorMarketDtoMap;
//	}
//
//
//	private static Map<String, MonitorMarketDto> transformSZMarket(QuotationMessage.QuotationPack quotationPack) {
//
//		Map<String, MonitorMarketDto> szMarketDtoMap = Maps.newHashMap();
//		List<QuotationMessage.QuotationPack.Quotation> quotations = quotationPack.getQuotesList();
//
//		for (QuotationMessage.QuotationPack.Quotation quotation : quotations) {
//			MonitorMarketDto monitorMarketDto = toSZMarket(quotationPack, quotation);
//			szMarketDtoMap.put(monitorMarketDto.getCode(), monitorMarketDto);
//		}
//		return szMarketDtoMap;
//	}
//
//	private static MonitorMarketDto toSZMarket(QuotationMessage.QuotationPack quotationPack, QuotationMessage.QuotationPack.Quotation quotation) {
//		MonitorMarketDto monitorMarketDto = new MonitorMarketDto();
//		try {
//			int i = 0;
//			monitorMarketDto.setCode(quotation.getValue(i++));
//			monitorMarketDto.setName(quotation.getValue(i++));
//			monitorMarketDto.setMarketSource(quotation.getValue(i++));
//			monitorMarketDto.setTotalAmt(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setOpenPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setPreviousPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setCurrentPrice(createBigDecimal(quotation.getValue(i++)));
//			i++;//涨跌幅字段不处理
//			//monitorMarketDto.setChangesPercent(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setState(quotation.getValue(i++));
//			monitorMarketDto.setTradeDate(parseTradeDate(quotation.getValue(i++)));
//
//			monitorMarketDto.setUpPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setLowPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setTotalNum(createBigDecimal(quotation.getValue(i++)));
//
//			monitorMarketDto.setSaleOnePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleTwoPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleThreePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleFourPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleFivePrice(createBigDecimal(quotation.getValue(i++)));
//
//			monitorMarketDto.setBuyOnePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyTwoPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyThreePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyFourPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyFivePrice(createBigDecimal(quotation.getValue(i++)));
//
//			if (monitorMarketDto.getPreviousPrice() != null && monitorMarketDto.getPreviousPrice().compareTo(BigDecimal.ZERO) != 0) {
//				monitorMarketDto.setChangesPercent(BigDecimal.valueOf(100).multiply(monitorMarketDto.getCurrentPrice().subtract(monitorMarketDto.getPreviousPrice())).divide(monitorMarketDto.getPreviousPrice(), 3, BigDecimal.ROUND_HALF_UP));
//			}
//			if (StockConfigurations.szSampledData.contains(monitorMarketDto.getCode())) {
//				logSampledData(quotationPack, monitorMarketDto);
//				//logger.info("securityType={}, quotation={}, monitorMarket={}", quotationPack.getSecuType(), quotation, monitorMarketDto);
//			}
//		} catch (Exception e) {
//			logger.error("securityType=" + quotationPack.getSecuType() + ", quotation=" + quotation + ", monitorMarketDto=" + monitorMarketDto, e);
//		}
//		return monitorMarketDto;
//	}
//
//	private static Map<String, MonitorMarketDto> transformSHIndex(QuotationMessage.QuotationPack quotationPack) {
//		Map<String, MonitorMarketDto> shMarketDtoMap = Maps.newHashMap();
//
//		List<QuotationMessage.QuotationPack.Quotation> quotations = quotationPack.getQuotesList();
//		for (QuotationMessage.QuotationPack.Quotation qta : quotations) {
//			MonitorMarketDto monitorMarketDto = toSHIndex(quotationPack, qta);
//			shMarketDtoMap.put(monitorMarketDto.getCode(), monitorMarketDto);
//		}
//		return shMarketDtoMap;
//	}
//
//	private static MonitorMarketDto toSHIndex(QuotationMessage.QuotationPack quotationPack, QuotationMessage.QuotationPack.Quotation quotation) {
//		int i = 0;
//		MonitorMarketDto monitorMarketDto = new MonitorMarketDto();
//		try {
//			String mdStreamID = quotation.getValue(i++);
//			if (!MDStreamID.Index.equalsValue(mdStreamID)) {
//				throw new RuntimeException("mdStreamID not match: current parser is index(MD001), receive mdStreamID=" + mdStreamID + ", quotation=" + quotation);
//			}
//			monitorMarketDto.setCode(quotation.getValue(i++));
//			monitorMarketDto.setName(quotation.getValue(i++));
//			monitorMarketDto.setMarketSource(ExchangeID.SH.getValue());
//			monitorMarketDto.setTotalNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setTotalAmt(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setPreviousPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setOpenPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setUpPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setLowPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setCurrentPrice(createBigDecimal(quotation.getValue(i++)));
//			if (monitorMarketDto.getPreviousPrice() != null && monitorMarketDto.getPreviousPrice().compareTo(BigDecimal.ZERO) != 0) {
//				monitorMarketDto.setChangesPercent(BigDecimal.valueOf(100).multiply(monitorMarketDto.getCurrentPrice().subtract(monitorMarketDto.getPreviousPrice())).divide(monitorMarketDto.getPreviousPrice(), 3, BigDecimal.ROUND_HALF_UP));
//			}
//			i++;//今收盘价，无取值取空格。
//			i++;//指数实时阶段及标志该字段为8位字符串，左起每位表示特定的含义，无定义则填空格。（预留）
//			monitorMarketDto.setTradeDate(parseTradeDate(quotation.getValue(i++)));
//
//			if (StockConfigurations.shSampledData.contains(monitorMarketDto.getCode())) {
//				logSampledData(quotationPack, monitorMarketDto);
//				//logger.info("securityType={}, quotation={}, monitorMarket={}", quotationPack.getSecuType(), quotation, monitorMarketDto);
//			}
//		} catch (Exception e) {
//			logger.error("securityType=" + quotationPack.getSecuType() + ", quotation=" + quotation + ", monitorMarketDto=" + monitorMarketDto, e);
//		}
//		return monitorMarketDto;
//	}
//
//
//
//	private static Map<String, MonitorMarketDto> transformSHStockFundBond(QuotationMessage.QuotationPack quotationPack) {
//		Map<String, MonitorMarketDto> shMarketDtoMap = Maps.newHashMap();
//
//		List<QuotationMessage.QuotationPack.Quotation> quotations = quotationPack.getQuotesList();
//		for (QuotationMessage.QuotationPack.Quotation qta : quotations) {
//			MonitorMarketDto monitorMarketDto = toSHStockFundBond(quotationPack, qta);
//			shMarketDtoMap.put(monitorMarketDto.getCode(), monitorMarketDto);
//		}
//		return shMarketDtoMap;
//	}
//
//
//	private static MonitorMarketDto toSHStockFundBond(QuotationMessage.QuotationPack quotationPack, QuotationMessage.QuotationPack.Quotation quotation) {
//		MonitorMarketDto monitorMarketDto = new MonitorMarketDto();
//		try {
//			int i = 0;
//			String mdStreamID = quotation.getValue(i++);
//			monitorMarketDto.setCode(quotation.getValue(i++));
//			monitorMarketDto.setName(quotation.getValue(i++));
//			monitorMarketDto.setMarketSource(ExchangeID.SH.getValue());
//			monitorMarketDto.setTotalNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setTotalAmt(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setPreviousPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setOpenPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setUpPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setLowPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setCurrentPrice(createBigDecimal(quotation.getValue(i++)));
//			i++;//今收盘价，无取值取空格。
//			monitorMarketDto.setBuyOnePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyOneNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleOnePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleOneNum(createBigDecimal(quotation.getValue(i++)));
//
//			monitorMarketDto.setBuyTwoPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyTwoNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleTwoPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleTwoNum(createBigDecimal(quotation.getValue(i++)));
//
//			monitorMarketDto.setBuyThreePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyThreeNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleThreePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleThreeNum(createBigDecimal(quotation.getValue(i++)));
//
//			monitorMarketDto.setBuyFourPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyFourNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleFourPrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleFourNum(createBigDecimal(quotation.getValue(i++)));
//
//			monitorMarketDto.setBuyFivePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setBuyFiveNum(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleFivePrice(createBigDecimal(quotation.getValue(i++)));
//			monitorMarketDto.setSaleFiveNum(createBigDecimal(quotation.getValue(i++)));
//
//			if (MDStreamID.Fund.equalsValue(mdStreamID)) {
//				i++;//PreCloseIOPV, 仅当MDStreamID=MD004时存在该字段。取前一日nav净值
//				i++;//IOPV, 仅当MDStreamID=MD004时存在该字段。
//			}
//
//			Optional<TradingPhaseCode> tradingPhaseCode = TradingPhaseCode.convert(quotation.getValue(i++));
//			if (tradingPhaseCode.isPresent()) {
//				monitorMarketDto.setState(tradingPhaseCode.get().getPeriodDesc().getDesc());
//			}
//			monitorMarketDto.setTradeDate(parseTradeDate(quotation.getValue(i++)));
//			if (monitorMarketDto.getPreviousPrice() != null && monitorMarketDto.getPreviousPrice().compareTo(BigDecimal.ZERO) != 0) {
//				monitorMarketDto.setChangesPercent(BigDecimal.valueOf(100).multiply(monitorMarketDto.getCurrentPrice().subtract(monitorMarketDto.getPreviousPrice())).divide(monitorMarketDto.getPreviousPrice(), 3, BigDecimal.ROUND_HALF_UP));
//			}
//
//			if (StockConfigurations.shSampledData.contains(monitorMarketDto.getCode())) {
//				logSampledData(quotationPack, monitorMarketDto);
//				//logger.info("securityType={}, quotation={}, monitorMarket={}", quotationPack.getSecuType(), quotation, monitorMarketDto);
//			}
//		} catch (Exception e) {
//			logger.error("securityType=" + quotationPack.getSecuType() + ", quotation=" + quotation + ", monitorMarketDto=" + monitorMarketDto, e);
//		}
//		return monitorMarketDto;
//	}
//
//
//	private static void logSampledData(QuotationMessage.QuotationPack quotationPack, MonitorMarketDto monitorMarketDto) {
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
//				.append("|").append(JodaDateUtil.TIMESTAMP_MS_FORMATTER.print(monitorMarketDto.getTradeDate().getTime())).append("|");
//		logger.info("securityType={}, quotation={}", quotationPack.getSecuType(), quotationLog);
//	}
//
//
//
//
//	public static BigDecimal createBigDecimal(final String str) {
//		return StringUtils.isBlank(str) ? null : new BigDecimal(str);
//	}
//
//	public static Date parseTradeDate(String tradeDate) {
//		try {
//			return TIMESTAMP_MS_FORMATTER.parseDateTime(tradeDate).toDate();
//		} catch (Exception e) {
//			logger.error("parse quotation date error, tradeDate parameter is " + tradeDate, e);
//			return null;
//		}
//	}
//
//}

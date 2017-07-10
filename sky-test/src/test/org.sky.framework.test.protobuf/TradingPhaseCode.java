package org.sky.framework.test.protobuf;

import com.google.common.base.Optional;
import hbec.commons.util.enums.ValuedEnumUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: mktdt00产品实时阶段及标志
 * <p>
 * 该字段为8位字符串，左起每位表示特定的含义，无定义则填空格。
 * 第1位：'S'表示启动（开市前）时段，'C'表示集合竞价时段，'T'表示连续交易时段，'B'表示休市时段，'E'表示闭市时段，'P'表示产品停牌，'M'表示可恢复交易的熔断时段（盘中集合竞价），'N'表示不可恢复交易的熔断时段（暂停交易至闭市），'D'表示开盘集合竞价阶段结束到连续竞价阶段开始之前的时段（如有）。
 * 第2位：'0'表示此产品不可正常交易，'1'表示此产品可正常交易，无意义填空格。
 * 第3位：'0'表示未上市，'1'表示已上市。
 * 第4位：'0'表示此产品在当前时段不接受进行新订单申报，'1' 表示此产品在当前时段可接受进行新订单申报。无意义填空格
 * <p>
 * Created by roc on 6/13/2017.
 */
public class TradingPhaseCode {

	public static final Logger LOGGER = LoggerFactory.getLogger(TradingPhaseCode.class);

	//第一位 表示产品的实时状态
	private String period;

	//第二位 是否可正常交易
	private boolean isTrade;

	//是否上市
	private boolean ipo;

	//是否可申报
	private boolean isDeclare;

	private PeriodDesc periodDesc;

	public static Optional<TradingPhaseCode> convert(String phaseCode) {

		try {
			if (StringUtils.isEmpty(phaseCode)) {
				return Optional.absent();
			}
			TradingPhaseCode tradingPhaseCode = new TradingPhaseCode();
			tradingPhaseCode.setPeriod(String.valueOf(phaseCode.charAt(0)));
			tradingPhaseCode.setTrade(phaseCode.charAt(1) == 1);
			tradingPhaseCode.setIpo(phaseCode.charAt(2) == 1);
			tradingPhaseCode.setDeclare(phaseCode.charAt(2) == 1);
			tradingPhaseCode.setPeriodDesc(ValuedEnumUtil.valueOf(tradingPhaseCode.getPeriod(), PeriodDesc.class));
			return Optional.of(tradingPhaseCode);
		} catch (Exception e) {
			LOGGER.error("convert phase code error, parameter phaseCode is " + phaseCode, e);
			return Optional.absent();
		}
	}

	public boolean isDelist(){
		return "P".equals(this.period);
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public boolean isTrade() {
		return isTrade;
	}

	public void setTrade(boolean trade) {
		isTrade = trade;
	}

	public boolean isIpo() {
		return ipo;
	}

	public void setIpo(boolean ipo) {
		this.ipo = ipo;
	}

	public boolean isDeclare() {
		return isDeclare;
	}

	public void setDeclare(boolean declare) {
		isDeclare = declare;
	}

	public PeriodDesc getPeriodDesc() {
		return periodDesc;
	}

	public void setPeriodDesc(PeriodDesc periodDesc) {
		this.periodDesc = periodDesc;
	}
}

//package org.sky.framework.test.protobuf;
//
//import hbec.commons.util.enums.ValuedEnum;
//
///**
// * Description: 表示产品的实时状态
// * 'S'表示启动（开市前）时段，'C'表示集合竞价时段，'T'表示连续交易时段，'B'表示休市时段，'E'表示闭市时段，'P'表示产品停牌，
// * 'M'表示可恢复交易的熔断时段（盘中集合竞价），
// * 'N'表示不可恢复交易的熔断时段（暂停交易至闭市），'D'表示开盘集合竞价阶段结束到连续竞价阶段开始之前的时段（如有）。
// * Created by roc on 6/13/2017 8:52 PM
// */
//
//public enum PeriodDesc implements ValuedEnum<String> {
//
//	Opening("S", "开市"),
//	CallAuction("C", "集合竞价"),
//	ContinuousTrading("T", "连续交易"),
//	Closed("B", "休市"),
//	Stop("E", "闭市"),
//	Suspension("P", "停牌"),
//	RecoverableCircuit("M", "可恢复交易的熔断"),
//	UnrecoverableCircuit("N", "不可恢复交易的熔断"),
//	BeforeContinuousCallAuction("D", "开盘集合竞价阶段结束到连续竞价阶段开始之前");
//
//	String period;
//	String desc;
//
//	@Override
//	public String getValue() {
//		return this.period;
//	}
//
//	@Override
//	public boolean equalsValue(String thatValue) {
//		return this.period != null && this.period.equals(thatValue);
//	}
//
//	PeriodDesc(String period, String desc) {
//		this.period = period;
//		this.desc = desc;
//	}
//
//	public String getPeriod() {
//		return period;
//	}
//
//	public void setPeriod(String period) {
//		this.period = period;
//	}
//
//	public String getDesc() {
//		return desc;
//	}
//
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
//}
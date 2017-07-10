package org.sky.framework.test.protobuf;

import hbec.commons.util.enums.ValuedEnum;

/**
 * Description: 行情数据类型标识符
 * Created by roc on 6/14/2017.
 */
public enum MDStreamID implements ValuedEnum<String>{

	Index("MD001"),
	Stock("MD002"),
	Bond("MD003"),
	Fund("MD004");

	private String mdStreamID;

	private MDStreamID(String mdStreamID) {
		this.mdStreamID = mdStreamID;
	}

	@Override
	public String getValue() {
		return this.mdStreamID;
	}

	@Override
	public boolean equalsValue(String thatValue) {
		return thatValue != null && mdStreamID.equals(thatValue);
	}

	public String getMdStreamID() {
		return mdStreamID;
	}

	public void setMdStreamID(String mdStreamID) {
		this.mdStreamID = mdStreamID;
	}
}

package org.sky.framework.test.algorithm;

public class HiloOptimizer {

	private String prefix;
	private int maxLo;
	private int lo;
	private long hi;
	private long lastValue;

	public HiloOptimizer(String prefix, int maxLo) {
		this.prefix = prefix != null ? prefix.replace("{", "${") : "";
		this.maxLo = maxLo;            //最大低位
		this.lo = maxLo + 1;           //最低初始位
	}

	public synchronized String generate() {
		if (lo > maxLo) {                    //当低位超过最大高位
			lastValue = getLastValue();      //表示hi位的进位次数，从数据库id管理表获取
			lo = lastValue == 0 ? 1 : 0;     //低位归0
			hi = lastValue * (maxLo + 1);    //高位进位
		}
		return String.valueOf(hi + lo++);    //低位最后自增lo++
	}

	public long getLastValue() {
		return lastValue;
	}
}

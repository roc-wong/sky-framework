package org.sky.framework.test.date;

import java.io.Serializable;

public class CacheEntity implements Serializable {
	private static final long serialVersionUID = -3971709196436977492L;
	private final int DEFUALT_VALIDITY_TIME = 30;// 默认过期时间 20秒

	private String cacheKey;
	private int validityTime;// 有效期时长，单位：秒
	private long timeoutStamp;// 过期时间戳

	private CacheEntity() {
		this.timeoutStamp = System.currentTimeMillis() + DEFUALT_VALIDITY_TIME * 1000;
		this.validityTime = DEFUALT_VALIDITY_TIME;
	}

	public CacheEntity(String cacheKey) {
		this();
		this.cacheKey = cacheKey;
	}

	public CacheEntity(String cacheKey, long timeoutStamp) {
		this(cacheKey);
		this.timeoutStamp = timeoutStamp;
	}

	public CacheEntity(String cacheKey, int validityTime) {
		this(cacheKey);
		this.validityTime = validityTime;
		this.timeoutStamp = System.currentTimeMillis() + validityTime * 1000;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public long getTimeoutStamp() {
		return timeoutStamp;
	}

	public void setTimeoutStamp(long timeoutStamp) {
		this.timeoutStamp = timeoutStamp;
	}

	public int getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(int validityTime) {
		this.validityTime = validityTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cacheKey == null) ? 0 : cacheKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheEntity other = (CacheEntity) obj;
		if (cacheKey == null) {
			if (other.cacheKey != null)
				return false;
		} else if (!cacheKey.equals(other.cacheKey))
			return false;
		return true;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CacheEntity{");
		sb.append("DEFUALT_VALIDITY_TIME=").append(DEFUALT_VALIDITY_TIME);
		sb.append(", cacheKey='").append(cacheKey).append('\'');
		sb.append(", validityTime=").append(validityTime);
		sb.append(", timeoutStamp=").append(timeoutStamp);
		sb.append('}');
		return sb.toString();
	}
}
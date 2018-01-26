package org.sky.rabbitmq.ratelimiter;

/**
 * @author roc
 * @date 2018/01/24
 */
public class LimiterResult {

    private boolean acquired;

    private Long remainderTTL;

    public boolean isAcquired() {
        return acquired;
    }

    public Long getRemainderTTL() {
        return remainderTTL;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LimiterResult{");
        sb.append("acquired=").append(acquired);
        sb.append(", remainderTTL=").append(remainderTTL);
        sb.append('}');
        return sb.toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private boolean acquired;
        private Long remainderTTL;

        private Builder() {
        }

        public Builder setAcquired(boolean acquired) {
            this.acquired = acquired;
            return this;
        }

        public Builder setRemainderTTL(Long remainderTTL) {
            this.remainderTTL = remainderTTL;
            return this;
        }

        public LimiterResult build() {
            LimiterResult limiterResult = new LimiterResult();
            limiterResult.acquired = this.acquired;
            limiterResult.remainderTTL = this.remainderTTL;
            return limiterResult;
        }
    }
}

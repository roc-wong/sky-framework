package org.sky.rabbitmq;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caosh on 2017/2/6.
 *
 * @author caoshuhao@touker.com
 */
public class Retry {
    private static final Logger logger = LoggerFactory.getLogger(Retry.class);

    public interface RetryAction<T> {
        T onTry() throws Exception;

        void onFailedOnce(Exception e, int retriedTimes);

        void onTriedOver(Exception e) throws Exception;

        void onUnexpectedException(Exception e) throws Exception;
    }

    public static abstract class BaseRetryAction<T> implements RetryAction<T> {
        @Override
        public void onFailedOnce(Exception e, int retriedTimes) {
            logger.warn("Try failed <{}> times, reason={}", retriedTimes, ExceptionUtils.getRootCauseMessage(e));
        }

        @Override
        public void onTriedOver(Exception e) throws Exception {
            throw e;
        }

        @Override
        public void onUnexpectedException(Exception e) throws Exception {
            throw e;
        }
    }

    private int times;
    private Class<? extends Exception> retryExceptionClass = Exception.class;
    private Predicate<Exception> exceptionFilter = Predicates.alwaysTrue();

    private Retry() {
    }

    private Retry setTimes(int times) {
        this.times = times;
        return this;
    }

    public static Retry times(int times) {
        return new Retry().setTimes(times);
    }

    public Retry onException(Class<? extends Exception> exceptionClass) {
        this.retryExceptionClass = Preconditions.checkNotNull(exceptionClass);
        return this;
    }

    public Retry onException(Predicate<Exception> exceptionFilter) {
        this.exceptionFilter = Preconditions.checkNotNull(exceptionFilter);
        return this;
    }

    private static class UnhandledException extends Exception {
        private static final long serialVersionUID = 1L;
    }

    public <T> T execute(RetryAction<T> retryAction) throws Exception {
        for (int i = 0; i < times; i++) {
            try {
                return retryAction.onTry();
            } catch (Exception e) {
                if (isRetriedException(e)) {
                    if (i == times - 1) {
                        // last time
                        retryAction.onTriedOver(e);
                    } else {
                        retryAction.onFailedOnce(e, i + 1);
                        // continue;
                    }
                } else {
                    retryAction.onUnexpectedException(e);
                }
            }
        }
        throw new UnhandledException();
    }

    private boolean isRetriedException(Exception e) {
        return retryExceptionClass.isAssignableFrom(e.getClass()) && exceptionFilter.apply(e);
    }
}

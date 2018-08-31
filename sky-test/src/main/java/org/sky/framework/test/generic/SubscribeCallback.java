package org.sky.framework.test.generic;

import java.util.List;

/**
 * @author roc
 * @date 2018/07/31
 */
public interface SubscribeCallback<T> {

    /**
     * 处理FIX推送的数据，仅支持JAVA版SDK
     * @return
     */
    boolean handleDelivery(List<T> data);

}

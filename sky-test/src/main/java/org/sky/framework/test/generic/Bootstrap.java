package org.sky.framework.test.generic;

import java.util.List;

/**
 * @author roc
 * @date 2018/08/01
 */
public class Bootstrap {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.invoke(new SubscribeCallback<Roc>() {
            @Override
            public boolean handleDelivery(List<Roc> data) {
                System.out.println(data);
                return false;
            }
        });
    }
}

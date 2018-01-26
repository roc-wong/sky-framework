package org.sky.rabbitmq;

import java.io.IOException;

/**
 * @author roc
 * @date 2018/01/23
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        PushMessageConsumer pushMessageConsumer = new PushMessageConsumer(1, 300);
    }
}

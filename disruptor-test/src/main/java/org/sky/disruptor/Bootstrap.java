package org.sky.disruptor;

/**
 * @author roc
 * @date 2018/01/08
 */
public class Bootstrap {
    public static void main(String[] args) {

        try {
            NotifyEventProducer producer = NotifyEventProducer.getInstance();

            for (int i = 0; i < 100; i++) {
                String message = "hello disruptor " + i;
                producer.send(message);
            }
            Thread.sleep(3000);

            for (int i = 100; i < 200; i++) {
                String message = "hello disruptor " + i;
                producer.send(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.sky.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class NotifyEventProducer {
    private final RingBuffer<NotifyEvent> ringBuffer;
    private static NotifyEventProducer instance;
    private int bufferSize = 1024;

    private NotifyEventProducer() {
        NotifyEventFactory factory = new NotifyEventFactory();
        Disruptor<NotifyEvent> disruptor = new Disruptor<>(factory, bufferSize
                , DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new NotifyEventHandler());
        ringBuffer = disruptor.start();
    }

    public synchronized static NotifyEventProducer getInstance() {
        if (instance == null) {
            instance = new NotifyEventProducer();
        }
        return instance;
    }

    public void send(String message) {
        // Grab the next sequence
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor
            NotifyEvent event = ringBuffer.get(sequence);
            // for the sequence
            // Fill with data
            event.setNotifyMsg(message);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}

package org.sky.disruptor;

import com.lmax.disruptor.EventFactory;

public class NotifyEventFactory implements EventFactory<NotifyEvent> {

	@Override
	public NotifyEvent newInstance() {
		return new NotifyEvent();
	}

}

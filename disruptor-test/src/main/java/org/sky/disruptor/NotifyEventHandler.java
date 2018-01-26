package org.sky.disruptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyEventHandler implements EventHandler<NotifyEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyEventHandler.class);

	public NotifyEventHandler() {
	}

	@Override
	public void onEvent(NotifyEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		LOGGER.info("receive event={}, sequence={}, endOfBatch={}", event, sequence, endOfBatch);
	}

}

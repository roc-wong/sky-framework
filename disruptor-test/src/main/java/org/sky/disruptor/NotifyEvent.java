package org.sky.disruptor;

public class NotifyEvent {

    private String notifyMsg;

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(NotifyEvent.class).omitNullValues()
                .addValue(NotifyEvent.class.getSuperclass() != Object.class ? super.toString() : null)
                .add("notifyMsg", notifyMsg)
                .toString();
    }
}

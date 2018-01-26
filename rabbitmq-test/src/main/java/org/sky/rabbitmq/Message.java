package org.sky.rabbitmq;

import java.io.Serializable;

/**
 * @author roc
 * @date 2018/01/24
 */
public class Message implements Serializable {

    private String content;

    private long date = System.currentTimeMillis();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("content='").append(content).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}

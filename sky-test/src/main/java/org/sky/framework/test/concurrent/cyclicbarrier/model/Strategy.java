package org.sky.framework.test.concurrent.cyclicbarrier.model;

import java.util.Date;

/**
 * @author roc
 * @date 2018/03/14
 */
public class Strategy {

    private Integer id;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Strategy{");
        sb.append("id=").append(id);
        sb.append(", createDate=").append(createDate);
        sb.append('}');
        return sb.toString();
    }
}

package org.sky.framework.test.generic;

/**
 * @author roc
 * @date 2018/08/01
 */
public class Roc {

    private String name;

    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Roc{");
        sb.append("name='").append(name).append('\'');
        sb.append(", height=").append(height);
        sb.append('}');
        return sb.toString();
    }
}

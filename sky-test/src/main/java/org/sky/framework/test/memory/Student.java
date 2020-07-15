package org.sky.framework.test.memory;

/**
 * @author roc
 * @since 2020/4/28 10:20
 */
public class Student {

    private int age;

    private String name;

    private double height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("age=")
          .append(age);
        sb.append(", name='")
          .append(name)
          .append('\'');
        sb.append(", height=")
          .append(height);
        sb.append('}');
        return sb.toString();
    }
}

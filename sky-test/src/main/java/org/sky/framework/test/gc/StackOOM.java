package org.sky.framework.test.gc;

/**
 * @author roc
 * @date 2019/1/4 15:09
 */
public class StackOOM {

    public static void main(String[] args) {
        new StackOOM().f();
    }

    public void f() {
        f();
    }
}

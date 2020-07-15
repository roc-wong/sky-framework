package org.sky.framework.test.math;

/**
 * @author roc
 * @date 2019/1/2 14:42
 */
public class ShiftTest {

    public static void main(String[] args) {
        int number = 10;
        //原始数二进制
        printInfo(number);
        number = number << 1;
        //左移一位
        printInfo(number);
        number = number >> 1;
        //右移一位
        printInfo(number);

        int a = 2 << 3;
        System.out.println(a);
    }

    /**
     * 输出一个int的二进制数
     *
     * @param num
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}

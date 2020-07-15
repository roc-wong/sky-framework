package org.sky.framework.test.io;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author roc
 * @since 2019/10/17 11:13
 */
public class IPTest {

    public static void main(String[] args) {
//        test();
        for (int i = 0; i <= 64; i++) {
            Double decimalism = Math.pow(2, i);
            NumberFormat numberFormat = NumberFormat.getIntegerInstance();
            numberFormat.setGroupingUsed(false);
            String binary = Long.toBinaryString(Double.valueOf(decimalism).longValue());
            System.out.println("| " + i + " | " + numberFormat.format(decimalism) + " | " + binary + " |");
        }

    }

    private static void test() {
        System.out.println(Integer.toBinaryString(15));
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toBinaryString(240));
        System.out.println(Integer.toBinaryString(224));
        System.out.println(Integer.toBinaryString(256));
        System.out.println(Integer.toBinaryString(255));
        System.out.println(Integer.toBinaryString(128));
        System.out.println(Integer.toBinaryString(127));
        System.out.println(Integer.toBinaryString(192));
        System.out.println(Integer.toBinaryString(191));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(29));
    }
}

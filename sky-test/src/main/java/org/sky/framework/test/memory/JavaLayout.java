package org.sky.framework.test.memory;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author roc
 * @since 2020/4/27 12:11
 */
public class JavaLayout {
    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();

        String classLayout = ClassLayout.parseInstance(obj)
                                        .toPrintable();
//
        System.out.println(classLayout);

        TimeUnit.SECONDS.sleep(6);

       synchronized(obj){
            classLayout = ClassLayout.parseInstance(obj)
                                            .toPrintable();
            System.out.println(classLayout);
        }


        /*synchronized(obj){
            classLayout = ClassLayout.parseInstance(obj)
                                     .toPrintable();
            System.out.println(classLayout);
        }*/

    }
}

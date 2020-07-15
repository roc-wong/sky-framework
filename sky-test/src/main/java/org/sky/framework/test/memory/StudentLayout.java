package org.sky.framework.test.memory;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author roc
 * @since 2020/4/27 12:11
 */
public class StudentLayout {
    public static void main(String[] args) throws InterruptedException {

        Student student = new Student();
        student.setAge(20);
        student.setName("roc");
        student.setHeight(175.4d);

        long l = GraphLayout.parseInstance(student)
                            .totalSize();

        System.out.println(l);
        String string = new String("sunshine");
        System.out.println(GraphLayout.parseInstance(string)
                            .totalSize());

        /*String classLayout = ClassLayout.parseInstance(obj)
                                        .toPrintable();
//
        System.out.println(classLayout);

        TimeUnit.SECONDS.sleep(6);

        synchronized (obj) {
            classLayout = ClassLayout.parseInstance(obj)
                                     .toPrintable();
            System.out.println(classLayout);
        }


        *//*synchronized(obj){
            classLayout = ClassLayout.parseInstance(obj)
                                     .toPrintable();
            System.out.println(classLayout);
        }*/

    }
}

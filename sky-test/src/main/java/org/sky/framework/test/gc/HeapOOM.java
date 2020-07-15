package org.sky.framework.test.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roc
 * @date 2019/1/4 15:12
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        while (true){
            list.add(String.valueOf(1));
        }
    }



}

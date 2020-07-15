package org.sky.framework.test.concurrent.dateformatter;

import java.text.SimpleDateFormat;

public class SimpleDateFormatDome {

    public static void main(String args[]) {


        //第一种：输出结果对不上
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String[] strs = {"2017-01-10", "2017-01-11", "2017-01-12", "2017-01-13", "2017-01-14", "2017-01-15"};

        SimpleDome[] domes = new SimpleDome[6];
        for (int i = 0; i < 6; i++) {
            domes[i] = new SimpleDome(simpleDateFormat, strs[i]);
        }

        for (int i = 0; i < 6; i++) {
            domes[i].start();
        } 
    }

    //第二种：报各种异常
    /*final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        new Thread(new Runnable() {
            public void run() {
                String str = "2017-01-10";
                Date date = new SimpleDome(simpleDateFormat2, str).stToDate();
                System.out.println(str + "=" + date);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                String str = "2017-01-11";
                Date date = new SimpleDome(simpleDateFormat2,str).stToDate();
                System.out.println(str + "=" + date);
            }
        }).start();*/
}
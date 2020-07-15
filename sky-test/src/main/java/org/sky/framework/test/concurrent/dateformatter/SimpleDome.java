package org.sky.framework.test.concurrent.dateformatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class SimpleDome extends Thread {

    private SimpleDateFormat simpleDateFormat;

    private String str;

    public SimpleDome(SimpleDateFormat sf, String str) {
        this.simpleDateFormat = sf;
        this.str = str;
    }

    @Override
    public void run() {
        try {
            Date date = simpleDateFormat.parse(str);
            System.out.println(str + "=" + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
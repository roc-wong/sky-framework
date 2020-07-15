package org.sky.framework.test.concurrent;

public class Apple {
    public int appleCount = 9;

    public static void main(String args[]) {
        Apple apple = new Apple();
        Thread m1 = new Thread(new Monkey(2, apple));
        Thread m2 = new Thread(new Monkey(3, apple));
        m1.start();
        m2.start();
    }


}

class Monkey implements Runnable {
    public int num;
    public Apple apple;

    Monkey(int num, Apple apple) {
        this.num = num;
        this.apple = apple;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (apple) {
                if (apple.appleCount - num < 0) {
                    break;
                }
                apple.appleCount = apple.appleCount - num;
                System.out.println("消费了" + num + "剩余" + apple.appleCount);
            }
        }
    }
}

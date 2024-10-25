package com.ake.hccp2.chapter1.lesson07;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 20:00
 *
 * 这是一个终止现成的反例
 * 主要是调用了stop后，会释放对应的线程的所有的同步锁，因为这个，会导致很多的问题。
 */
public class StopExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                System.out.println("Running...." + i);
            }
        });

        t1.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.stop();
        System.out.println("try to print finally....");

    }
}

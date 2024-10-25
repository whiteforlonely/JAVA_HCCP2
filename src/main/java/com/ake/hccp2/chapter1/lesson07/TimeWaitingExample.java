package com.ake.hccp2.chapter1.lesson07;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 19:39
 */
public class TimeWaitingExample {

    public static void main(String[] args) {
        new Thread(()-> {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "TIME_WAITING_THREAD").start();
    }
}

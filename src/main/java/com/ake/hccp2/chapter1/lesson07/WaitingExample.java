package com.ake.hccp2.chapter1.lesson07;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 19:46
 */
public class WaitingExample {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                synchronized (WaitingExample.class) {
                    System.out.println("come to waiting thread.....");
                    WaitingExample.class.wait();
                    System.out.println("come to wake up thread.....");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "WAITING_AKE_THREAD_1").start();
        Thread.sleep(10000);
        new Thread(()->{
            synchronized (WaitingExample.class) {
                // 这边能够执行到，说明在上面的线程中已经释放了锁，就是wait的时候，会把锁释放掉。
                System.out.println("come to notify thread.....");
                WaitingExample.class.notify();
            }
        }, "WAITING_AKE_THREAD_2").start();
    }
}

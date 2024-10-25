package com.ake.hccp2.chapter1.lesson08;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 20:36
 *
 * 尝试终端被阻塞的线程
 */
public class InterruptBlockedTreadExample {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            synchronized (InterruptBlockedTreadExample.class){
                System.out.println("thread 1 is running....");
                while (true) {
                    // 这边一直获得锁
                }
            }
        });
        t1.start();

        // 另外一个线程也是获取同样的锁导致被中断
        Thread t2 = new Thread(()->{
            System.out.println("thread 2 is waiting....");
            synchronized (InterruptBlockedTreadExample.class){
                System.out.println("thread 2 is running....");
            }
        });
        t2.start();

        // 现在尝试中断线程2，这边直接尝试终端是没有用的，那应该怎么做可以终端线程2
        // 线程2一直被挂起，
        System.out.println("try to send interrupt thread 2");
        t2.interrupt();
    }
}

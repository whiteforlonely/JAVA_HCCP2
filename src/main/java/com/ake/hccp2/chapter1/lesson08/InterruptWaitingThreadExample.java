package com.ake.hccp2.chapter1.lesson08;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 20:48
 *
 * 尝试终端等在等待的线程
 */
public class InterruptWaitingThreadExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("thread 1 is running....");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                // 这个时候，线程的中断标志已经被复位，所以这边打印false，同时可以在这边处理其他的相关的后续的操作。
                System.out.println("in thread before throw exception thread " + Thread.currentThread().isInterrupted());
//                throw new RuntimeException(e);
            }
        });
        t1.start();

        System.out.println("try to send interrupt thread1");
        // 这边针对于等待的线程，通过interrupt方法会直接的抛出异常，终端线程，但是被阻塞的话，是没有办法的~~~
        t1.interrupt();
        System.out.println("after to send interrupt thread " + t1.isInterrupted());
    }
}

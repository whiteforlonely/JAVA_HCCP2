package com.ake.hccp2.chapter1.lesson08;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 20:21
 *
 * 针对于这个interrupt，现在可以更清晰的明白，interrupt只是设置了一个中断标志位，但是并没有中断线程的执行
 * 需要线程自己去判断，是否要中断，
 *
 * 这个是在运行中的线程，如果是一个等待的宣称或者是一个阻塞的线程呢，是否可以直接的通过interrupt让线程终端
 * 呢？
 */
public class InterruptExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("thread is interrupted");
                    break;
                }
            }
        });
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("try to interrupt thread....., isInterrupted = " + t1.isInterrupted());
        t1.interrupt();
        System.out.println("after to interrupt thread....., isInterrupted = " + t1.isInterrupted());
    }
}

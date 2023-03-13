package com.ake.hccp2.chapter6.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class DebugAqsTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start to running");
                    Thread.sleep(30000);
                    latch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread exec end~~~~");

    }
}

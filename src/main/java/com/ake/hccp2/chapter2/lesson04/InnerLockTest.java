package com.ake.hccp2.chapter2.lesson04;

import org.openjdk.jol.vm.VM;

import java.util.concurrent.CountDownLatch;

// 省略import
public class InnerLockTest {
    //偏向锁的案例演示
    public static void main(String[] args) throws InterruptedException {
        System.out.println(VM.current().details());
        //JVM延迟偏向锁
        Thread.sleep(5000);
        ObjectLock lock = new ObjectLock();
        System.out.println("占锁之前： ");
        lock.printSelf();
        Thread.sleep(5000);
        CountDownLatch latch = new CountDownLatch(1);
        Runnable runnable = () ->
        {
            for (int i = 0; i < 1000; i++) {
                synchronized (lock) {
                    lock.increase();
                    if (i == 1000 / 2) {
                        System.out.println("占有锁, lock的状态: ");
                        lock.printSelf();
                    }
                }
                //每一次循环等待10毫秒
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            latch.countDown();
        };
        new Thread(runnable, "biased-demo-thread").start();
        //等待加锁线程执行完成
        latch.await();
        Thread.sleep(5000);
        System.out.println("释放锁后, lock的状态: ");
        lock.printSelf();
    }
    // 省略不相干代码
}
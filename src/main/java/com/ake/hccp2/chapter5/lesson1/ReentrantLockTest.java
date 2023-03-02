package com.ake.hccp2.chapter5.lesson1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            lock.lock();
            try{
                System.out.println("thread 1, start to do something...");
                Thread.sleep(5000);
                System.out.println("thread 1, end to do something...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try{
                System.out.println("thread 2 start to run");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("start to end this thread...... 2");
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();

//        lock.lockInterruptibly();

        new Thread(()->{
            lock.lock();
            try{
                System.out.println("thread 3 start to run");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("start to end this thread...... 3");
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();
    }
}

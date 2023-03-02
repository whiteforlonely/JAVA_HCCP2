package com.ake.hccp2.chapter5.lesson2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 简单的通过CAS实现一个自旋锁
 */
public class SpinLock implements Lock {

    private AtomicReference<Thread> owner = new AtomicReference<>();
    private int count = 0;

    @Override
    public void lock() {

        Thread thread = Thread.currentThread();
        if (owner.get() == thread) {
            ++count;
        }
        while (owner.compareAndSet(null, thread)) {
            Thread.yield();
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        Thread thread = Thread.currentThread();
        if (thread == owner.get()) {
            if (count > 0) {
                count--;
            } else {
                owner.set(null);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

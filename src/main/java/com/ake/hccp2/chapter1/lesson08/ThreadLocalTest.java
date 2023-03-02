package com.ake.hccp2.chapter1.lesson08;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    static class Foo {
        static final AtomicInteger AMOUNT = new AtomicInteger(0);
        int index = 0;
        int bar = 10;
        public Foo(){
            index = AMOUNT.incrementAndGet();
        }

        @Override
        public String toString() {
            return index + "@Foo{bar=" + bar + "}";
        }
    }

    private static final ThreadLocal<Foo> LOCAL_FOO = new ThreadLocal<Foo>();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100), Executors.defaultThreadFactory());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                if (LOCAL_FOO.get() == null) {
                    LOCAL_FOO.set(new Foo());
                }
                System.out.println(Thread.currentThread().getName() + "===> 初始化的本底值： " +LOCAL_FOO.get());
            });
        }

        for (int i = 0; i < 10; i++) {
            
        }
    }
}

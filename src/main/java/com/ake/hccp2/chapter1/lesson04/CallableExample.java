package com.ake.hccp2.chapter1.lesson04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/24 20:26
 *
 * callable 和 running是一致的，只是多了一个返回值。中间需要封装一个task。
 */
public class CallableExample implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("test thread return value");
        return "success";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableExample callableExample = new CallableExample();
        FutureTask<String> future = new FutureTask<>(callableExample);
        Thread thread = new Thread(future);
        thread.start();
        String s = future.get();

        System.out.println("run result : " + s);
    }
}

package com.ake.hccp2.chapter1.lesson04;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/24 20:22
 */
public class RunnableThreadExample implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable thread is running...");
    }

    public static void main(String[] args) {
        RunnableThreadExample runnableThreadExample = new RunnableThreadExample();
        Thread thread = new Thread(runnableThreadExample);
        thread.start();
    }
}

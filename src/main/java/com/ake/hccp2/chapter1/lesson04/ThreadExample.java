package com.ake.hccp2.chapter1.lesson04;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/24 20:23
 */
public class ThreadExample extends Thread{

    public void run(){
        System.out.println("Thread is running");
    }
    public static void main(String[] args) {
        new ThreadExample().start();
    }
}

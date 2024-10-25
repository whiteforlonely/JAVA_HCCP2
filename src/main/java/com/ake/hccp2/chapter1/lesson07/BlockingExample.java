package com.ake.hccp2.chapter1.lesson07;

/**
 * @author saturday
 * @version 1.0.0
 * date: 2024/10/25 19:55
 */
public class BlockingExample {

    /**
     * "AKE_THREAD_2" #13 prio=5 os_prio=0 tid=0x000001f1d4fc9800 nid=0x900 waiting for monitor entry [0x000000ad190ff000]
     *    java.lang.Thread.State: BLOCKED (on object monitor)
     *         at com.ake.hccp2.chapter1.lesson07.BlockingExample.lambda$main$1(BlockingExample.java:23)
     *         - waiting to lock <0x000000076e4082b0> (a java.lang.Class for com.ake.hccp2.chapter1.lesson07.BlockingExample)
     *         at com.ake.hccp2.chapter1.lesson07.BlockingExample$$Lambda$2/1747585824.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     *
     * "AKE_THREAD_1" #12 prio=5 os_prio=0 tid=0x000001f1d4fc8800 nid=0x7db8 runnable [0x000000ad18fff000]
     *    java.lang.Thread.State: RUNNABLE
     *         at com.ake.hccp2.chapter1.lesson07.BlockingExample.lambda$main$0(BlockingExample.java:14)
     *         - locked <0x000000076e4082b0> (a java.lang.Class for com.ake.hccp2.chapter1.lesson07.BlockingExample)
     *         at com.ake.hccp2.chapter1.lesson07.BlockingExample$$Lambda$1/1096979270.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     */
    public static void main(String[] args) {
        new Thread(()-> {
            synchronized (BlockingExample.class) {
                System.out.println("hold lock in this thread 1.....");
                while (true) {
                    // 一直持有锁
                }
            }
            }, "AKE_THREAD_1").start();

        new Thread(()-> {
            // this thread was blocked
            synchronized (BlockingExample.class) {
                System.out.println("hold lock in this thread 2.....");
            }
            }, "AKE_THREAD_2").start();
    }
}

package com.ake.hccp2.chapter5.lesson2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CLHLock implements Lock {

    private ThreadLocal<Node> curNodeLocal =new ThreadLocal<>();
    private AtomicReference<Node> tail = new AtomicReference<>();

    public CLHLock(){
        tail.getAndSet(Node.EMPTY);
    }
    @Override
    public void lock() {
        Node currNode = new Node(true, null);
        Node preNode = tail.get();
        while (!tail.compareAndSet(preNode, currNode)) {
            preNode = tail.get();
        }
        currNode.preNode = preNode;
        while (currNode.preNode.locked) {
            // 进行普通自旋等待获取锁
            Thread.yield();
        }

        // 执行到这边，说明获得锁成功了。
        curNodeLocal.set(currNode);
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
        Node curNode = curNodeLocal.get();
        curNode.locked = false;
        curNode.preNode = null;
        curNodeLocal.set(null); // 这个线程可能下一次还会抢锁
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    static class Node{
        volatile boolean locked;

        Node preNode;

        public Node(boolean locked, Node preNode){
            this.locked =locked;
            this.preNode = preNode;
        }

        public static final Node EMPTY = new Node(false, null);
    }
}

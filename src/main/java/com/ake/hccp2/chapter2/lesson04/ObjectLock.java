package com.ake.hccp2.chapter2.lesson04;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class ObjectLock {

    private Integer amount = 0;

    public void increase(){
        synchronized (this) {
            amount ++;
        }
    }

    public String hexHash(){
        int hashCode = this.hashCode();
        byte[] hashCode_LE = int2Bytes_LE(hashCode);
        return byteToHex(hashCode_LE);
    }

    private byte[] int2Bytes_LE(int val){
        byte[] data = new byte[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (val & 0xff);
            val = val >> 8;
        }
        return data;
    }

    private byte[] long2Bytes_LE(long val){
        byte[] data = new byte[8];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (val & 0xff);
            val = val >> 8;
        }
        return data;
    }

    private String byteToHex(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte datum : data) {
            String intHex = Integer.toHexString(datum&0xff);
            builder.append(intHex);
        }
        return builder.toString();
    }

    public String binaryHash(){
        int hashCode = this.hashCode();
        byte[] hashCode_LE = int2Bytes_LE(hashCode);
        StringBuilder buffer = new StringBuilder();
        for (byte b: hashCode_LE) {
            String binary = Integer.toBinaryString(b&0xff);
            buffer.append(binary).append(" ");
        }
        return buffer.toString();
    }

    public String hexThreadId(){
        long threadId = Thread.currentThread().getId();
        byte[] threadId_LE = long2Bytes_LE(threadId);
        return byteToHex(threadId_LE);
    }

    public String binaryThreadId(){
        long threadID = Thread.currentThread().getId();
        byte[] threadID_LE = long2Bytes_LE(threadID);
        StringBuilder buffer = new StringBuilder();
        for (byte b: threadID_LE) {
            String binary = Integer.toBinaryString(b);
            buffer.append(binary).append(" ");
        }
        return buffer.toString();
    }

    public void printSelf(){
        System.out.println("Hex hash: " + hexHash());
        System.out.println("binary hash: " + binaryHash());
        System.out.println("hex threadId: " + hexThreadId());
        System.out.println("binary threadId: " + binaryThreadId());

        String printable = ClassLayout.parseInstance(this).toPrintable();
        System.out.println("lock = " + printable);
    }

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        ObjectLock objectLock = new ObjectLock();
        System.out.println("Object details: ");
        objectLock.printSelf();
    }
}

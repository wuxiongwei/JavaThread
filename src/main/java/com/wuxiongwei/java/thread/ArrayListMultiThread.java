package com.wuxiongwei.java.thread;

import java.util.ArrayList;

/**
 并发下的ArrayList
 会报ArrayIndexOutOfBoundsException
 因为ArrayList在扩容过程中，内部一致性被破坏，由于没有锁的保护，另外一个线程访问到了不一致的内部状态，导致出现越界问题。
 */
public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<>(10);//错误示范
//    static List<Integer> al = Collections.synchronizedList(new ArrayList<>(10));
//    static CopyOnWriteArrayList al = new CopyOnWriteArrayList();//读多场景
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000000 ; i++) {
                System.out.println(i);
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}

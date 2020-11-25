package com.wuxiongwei.java.thread;

import java.util.concurrent.locks.ReentrantLock;

/**

 */
public class ReentrantLockTest implements Runnable{
    public static ReentrantLock lock  = new ReentrantLock();
    public static int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
//            lock.lock(); //可以锁多次
            try {
                i++;
            } finally {
                lock.unlock();
//                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}

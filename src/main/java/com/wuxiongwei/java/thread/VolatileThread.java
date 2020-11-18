package com.wuxiongwei.java.thread;

/**
 在虚拟机的Client模式下，由于JIT并没有做足够的优化，在主线程修改ready变量的状态后，ReaderThread可以发现这个改动，并退出程序。
 但是在Server模式下，由于系统优化的结果，ReaderThread线程无法“看到”主线程中的修改，导致ReaderThread永远无法退出（因为代码第7行判断永远不会成立），这显然不是我们想看到的结果。
 这个问题就是一个典型的可见性问题。注意：可以使用Java虚拟机参数-server切换到Server模式。
 */
public class VolatileThread {
    private static volatile boolean ready;
    private static  int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(1000);
    }
}

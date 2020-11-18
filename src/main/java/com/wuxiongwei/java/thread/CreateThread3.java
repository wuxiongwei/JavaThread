package com.wuxiongwei.java.thread;

/**
 */
public class CreateThread3 implements Runnable {
    public static void main(String[] args) {
        Thread tl = new Thread(new CreateThread3());
        tl.start();
    }

    public void run() {
        System.out.println("Oh, I am Runnable");
    }
}

package com.wuxiongwei.java.thread;

/**

 */
public class ThreadGroupTest implements Runnable{
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("group");
        Thread t1 = new Thread(tg, new ThreadGroupTest(),"T1");
        Thread t2 = new Thread(tg, new ThreadGroupTest(),"T2");
        t1.start();
        t2.start();
        //activeCount()方法可以获得活动线程的总数，但由于线程是动态的，因此这个值只是一个估计值，无法精确；
        System.out.println(tg.activeCount());
        //list()方法可以打印这个线程组中所有的线程信息，对调试有一定帮助。
        tg.list();
    }

    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while(true){
            System.out.println("打印组名和线程名"+groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

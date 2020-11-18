package com.wuxiongwei.java.thread;

/**

 */
public class DaemonThread {
    public static class DaemonTest extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("我还活着");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonTest();
        t.setDaemon(true);//守护线程，当main线程退出时守护线程自动退出
        //设置守护线程必须在线程start()之前设置，否则会抛异常，并被当作用户线程而已。
        t.start();
        Thread.sleep(2000);
    }
}

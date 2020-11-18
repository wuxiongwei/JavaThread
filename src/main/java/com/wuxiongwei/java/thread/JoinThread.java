package com.wuxiongwei.java.thread;

import javax.xml.transform.Source;

/**

 */
public class JoinThread {
    public volatile static int i = 0;
    public  static  class AddThread extends  Thread{
        @Override
        public void run() {
            for(i=0;i<10000000;i++){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();//等待线程执行完成后main再往下执行
        System.out.println(i);
    }
}

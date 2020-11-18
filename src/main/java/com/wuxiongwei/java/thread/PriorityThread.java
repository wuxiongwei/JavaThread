package com.wuxiongwei.java.thread;

/**
 在对count累加前，我们使用关键字synchronized产生了一次资源竞争，目的是使得优先级的差异表现得更为明显。

 我这里测试不太正确（不知道为什么）
 */
public class PriorityThread {
    static int size = 10000000;
    public static class HightPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityThread.class){
                    count++;
                    if(count>size){
                        System.out.println("高优先级完成");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityThread.class){
                    count++;
                    if(count>size){
                        System.out.println("低优先级完成");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i =0;i<10;i++){
            Thread high = new HightPriority();
            Thread low = new LowPriority();
            high.setPriority(Thread.MAX_PRIORITY);
            low.setPriority(Thread.MIN_PRIORITY);
            low.start();
            high.start();
            Thread.sleep(3000);
            System.out.println("====");
        }
    }

}

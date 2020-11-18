package com.wuxiongwei.java.thread.stopthread;

/**
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        interrupt3();
    }

    //打断不了
    private static void interrupt1() throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("running");
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        //在这里，虽然对t1进行了中断，但是在t1中并没有中断处理的逻辑，因此，即使t1线程被置为中断状态，这个中断也不会发生任何作用。
        t1.interrupt();
    }

    //可以正常打断
    private static void interrupt2() throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interruted!");
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

    /**
     如果线程被中断，则程序会抛出异常，并处理。
     在catch子句部分，由于已经捕获了中断，我们可以立即退出线程。
     但在这里，我们并没有这么做，因为也许在这段代码中，我们还必须进行后续的处理来保证数据的一致性和完整性，
     因此，执行了Thread.interrupt()方法再次中断自己，置上中断标记位。
     只有这么做，在中断检查中，才能发现当前线程已经被中断了。
     */
    private static void interrupt3() throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interruted!");
                        break;
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("睡觉的时候被打断");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}

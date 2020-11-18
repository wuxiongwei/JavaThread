package com.wuxiongwei.java.thread.stopthread;

/**
 */
public class StopThreadSafe {
    public static User u = new User();
    public static class User{
        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ChangeObjectThread extends Thread{
        volatile boolean stop = false;
        public void stopMe(){
            stop = true;
        }
        @Override
        public void run() {
            while (true){
                if(stop){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u){
                    int v = (int) (System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId()!=Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true){
            ChangeObjectThread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            /**
             标记变量stop，用于指示线程是否需要退出。
             当stopMe()方法被调用，stop就被设置为true，此时，线程就自动退出了。
             使用这种方式退出线程，不会使对象u的状态出现错误。
             因为，ChangeObjectThread已经没有机会“写坏”对象了，它总是会选择在一个合适的时间终止线程。
             */
            t.stopMe();
        }
    }

}


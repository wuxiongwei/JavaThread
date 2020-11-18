package com.wuxiongwei.java.thread.stopthread;

/**
 */
public class StopThreadUnsafe {
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
        @Override
        public void run() {
            while (true){
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
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            //为什么stop()方法被废弃而不推荐使用呢？原因是stop()方法过于暴力，强行把执行到一半的线程终止，可能会引起一些数据不一致的问题。
            t.stop();
            //如果在线上环境跑出以上结果，那么加班估计是免不了了，因为这类问题一旦出现，就很难排查，它们甚至没有任何错误信息，也没有线程堆栈。这种情况一旦混杂在动则十几万行的程序代码中时，发现它们就全凭经验、时间以及一点点运气了。因此，除非你很清楚自己在做什么，否则不要随便使用stop()方法来停止一个线程。
        }
    }

}


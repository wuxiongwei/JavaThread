package com.wuxiongwei.java.thread.akka.blog.a2;


import akka.actor.UntypedAbstractActor;

public class Greeter extends UntypedAbstractActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object msg) throws InterruptedException {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            Thread.sleep(1000);
            getSender().tell(Msg.DONE, getSelf());
        } else
            unhandled(msg);
    }

}

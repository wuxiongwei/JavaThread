package com.wuxiongwei.java.thread.akka.demo1;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;

/**
 */
public class Greeter extends AbstractActor {

    private final String message;
    private final ActorRef printerActor;
    private String greeting = "";

    public Greeter(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    static public Props props(String message, ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(message, printerActor));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WhoToGreet.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                })
                .match(Greet.class, x -> {
                    printerActor.tell(new Printer.Greeting(greeting), getSelf());
                })
                .build();
    }

    static public class WhoToGreet {
        public final String who;
        public WhoToGreet(String who) {
            this.who = who;
        }
    }
    static public class Greet {
        public Greet() {
        }
    }

}

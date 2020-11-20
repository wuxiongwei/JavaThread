package com.wuxiongwei.java.thread.akka.demo1;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 */
public class Printer extends AbstractActor {

    public Printer() {
    }

    static public Props props() {
        return Props.create(Printer.class, () -> new Printer());
    }


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, greeting -> {
                    log.info(greeting.message);
                })
                .build();
    }

    static public class Greeting {
        public final String message;
        public Greeting(String message) {
            this.message = message;
        }
    }

}

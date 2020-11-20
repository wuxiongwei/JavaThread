package com.wuxiongwei.java.thread.akka.demo3;

import java.io.IOException;


import java.io.IOException;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;

public class IotMain {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("iot-system");

        try {
            // Create top level supervisor
            ActorRef supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor");

            System.out.println("Press ENTER to exit the system");
            System.in.read();
        } finally {
            system.terminate();
        }
    }
}

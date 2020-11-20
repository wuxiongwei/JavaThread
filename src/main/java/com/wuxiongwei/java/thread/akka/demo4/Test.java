package com.wuxiongwei.java.thread.akka.demo4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 */
public class Test {
    @org.junit.Test
    public void test1() {
        ActorSystem system = ActorSystem.create("device-system");
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));
        deviceActor.tell(new Device.ReadTemperature(42L), probe.getRef());
        Device.RespondTemperature response = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(42L, response.requestId);
        assertEquals(Optional.empty(), response.value);
    }
}

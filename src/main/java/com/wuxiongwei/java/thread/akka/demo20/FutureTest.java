package com.wuxiongwei.java.thread.akka.demo20;

import akka.actor.ActorSystem;
import akka.pattern.Patterns;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import akka.pattern.Patterns;

public class FutureTest {

    public static void main(String[] args) {
//        ActorSystem system = ActorSystem.create("future-system");
//        CompletionStage<String> failWithException =
//                CompletableFuture.supplyAsync(
//                        () -> {
//                            throw new IllegalStateException("OHNOES1");
//                        });
//        CompletionStage<String> delayed =
//                Patterns.after(Duration.ofMillis(200), system, () -> failWithException);
//
//
//
//        Callable<CompletionStage<String>> attempt = () -> CompletableFuture.completedFuture("test");
//
//        CompletionStage<String> retriedFuture =
//                Patterns.retry(attempt, 3, java.time.Duration.ofMillis(200), system);

    }
}

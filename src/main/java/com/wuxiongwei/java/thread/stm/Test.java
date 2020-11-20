package com.wuxiongwei.java.thread.stm;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 https://www.baeldung.com/java-multiverse-stm
 */
public class Test {
    @org.junit.Test
    public void givenAccount_whenDecrement_thenShouldReturnProperValue() {
        Account a = new Account(10);
        a.adjustBy(-5);

        assertEquals(Integer.valueOf(5),a.getBalance());
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void givenAccount_whenDecrementTooMuch_thenShouldThrow() {
        // given
        Account a = new Account(10);

        // when
        a.adjustBy(-11);


    }

    @org.junit.Test
    public void test3() throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Account a = new Account(10);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean exceptionThrown = new AtomicBoolean(false);

        ex.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                a.adjustBy(-6);
            } catch (IllegalArgumentException e) {
                exceptionThrown.set(true);
            }
        });
        ex.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                a.adjustBy(-5);
            } catch (IllegalArgumentException e) {
                exceptionThrown.set(true);
            }
        });

        countDownLatch.countDown();
        ex.awaitTermination(1, TimeUnit.SECONDS);
        ex.shutdown();

        assertTrue(exceptionThrown.get());
        System.out.println(a.getBalance());

    }

    @org.junit.Test
    public void test4(){
        Account a = new Account(10);
        Account b = new Account(10);

        a.transferTo(b, 5);

        assertEquals(Integer.valueOf(5),a.getBalance());
        assertEquals(Integer.valueOf(15),b.getBalance());

        try {
            a.transferTo(b, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("failed to transfer money");
        }

        assertEquals(Integer.valueOf(5),a.getBalance());
        assertEquals(Integer.valueOf(15),b.getBalance());

    }

    /**
     * we do not need to worry about deadlocks as the STM is Deadlock Safe
     * @throws InterruptedException
     */
    @org.junit.Test
    public void deadLock() throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Account a = new Account(10);
        Account b = new Account(10);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ex.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.transferTo(b, 10);
        });
        ex.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b.transferTo(a, 1);

        });

        countDownLatch.countDown();
        ex.awaitTermination(1, TimeUnit.SECONDS);
        ex.shutdown();

//        assertEquals(Integer.valueOf(1),a.getBalance());
//        assertEquals(Integer.valueOf(19),b.getBalance());
        System.out.println("a "+a.getBalance());
        System.out.println("b "+b.getBalance());

    }

    @org.junit.Test
    public void deadLock2() throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Account a = new Account(100);
        Account b = new Account(100);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ex.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.transferTo(b, 10);
        });
        ex.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b.transferTo(a, 1);

        });

        countDownLatch.countDown();
        ex.awaitTermination(1, TimeUnit.SECONDS);
        ex.shutdown();

        assertEquals(Integer.valueOf(91),a.getBalance());
        assertEquals(Integer.valueOf(109),b.getBalance());

    }

}

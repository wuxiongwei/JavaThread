package com.wuxiongwei.java.thread.stm2;

import org.junit.Assert;
import org.multiverse.api.StmUtils;
import org.multiverse.api.references.TxnInteger;
import org.multiverse.api.references.TxnLong;

/**
 */
public class Account {
    private TxnLong lastUpdate;
    private TxnInteger balance;
    private TxnInteger balance2;

    public Account(int balance) {
        this.lastUpdate = StmUtils.newTxnLong(System.currentTimeMillis());
        this.balance = StmUtils.newTxnInteger(balance);
        this.balance2 = StmUtils.newTxnInteger(balance);
    }

    public void adjustBy(int amount) {
        adjustBy(amount, System.currentTimeMillis());
    }

    public void adjustBy(int amount, long date) {
        StmUtils.atomic(() -> {
//            for(int i = 0;i<10000000;i++){
//                System.out.println(i);
//            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
            balance.increment(amount);
            lastUpdate.set(date);
            System.out.println(" amount: "+amount+" date: "+date);
            if (balance.get() <= 0) {
                System.out.println("Not enough money");
                throw new IllegalArgumentException("Not enough money");
            }
            balance2.increment(amount);
        });
    }

    public Integer getBalance() {
        System.out.println("balance "+balance.atomicGet());
        System.out.println("balance2 "+balance2.atomicGet());
        Assert.assertTrue(balance.atomicGet()==balance2.atomicGet());
        return balance.atomicGet();
    }

    public void transferTo(Account other, int amount) {
        StmUtils.atomic(() -> {
            long date = System.currentTimeMillis();
            adjustBy(-amount, date);
            other.adjustBy(amount, date);
        });
    }

}

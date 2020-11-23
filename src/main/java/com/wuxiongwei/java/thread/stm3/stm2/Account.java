package com.wuxiongwei.java.thread.stm3.stm2;

import org.multiverse.api.StmUtils;
import org.multiverse.api.collections.TxnMap;
import org.multiverse.api.references.TxnLong;

/**
 */
public class Account {
    private TxnLong lastUpdate;
    private TxnMap balance=StmUtils.newTxnHashMap();
    private TxnMap balance2=StmUtils.newTxnHashMap();

    public Account() {
        this.lastUpdate = StmUtils.newTxnLong(System.currentTimeMillis());
        this.balance.put("_name",10);
        this.balance2.put("_name",10);
    }

    public void adjustBy(int amount) {
        adjustBy(amount, System.currentTimeMillis());
    }

    public void adjustBy(int amount, long date) {
        StmUtils.atomic(() -> {
            balance.put("_name",balance.get("_name"));

            lastUpdate.set(date);
            System.out.println(" amount: "+amount+" date: "+date);
            if (Integer.valueOf(balance.get("_name").toString()) <= 0) {
                System.out.println("Not enough money");
                throw new IllegalArgumentException("Not enough money");
            }
            balance.put("_name",balance2.get("_name"));
        });
    }

    public Integer getBalance() {
        System.out.println("balance "+balance.get("_name"));
        System.out.println("balance2 "+balance2.get("_name"));
        return 0;
    }

    public void transferTo(Account other, int amount) {
        StmUtils.atomic(() -> {
            long date = System.currentTimeMillis();
            adjustBy(-amount, date);
            other.adjustBy(amount, date);
        });
    }

}

package com.wuxiongwei.java.thread.akka.demo50.stm2;

import akka.actor.UntypedActor;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 */
public class CompanyActor extends UntypedActor {
    private Ref.View<Integer> cou = STM.newRef(100);//定义账号余额
    @Override
    public void onReceive(Object message) throws Exception, Exception {
//        if(message instanceof Corr)
    }
}

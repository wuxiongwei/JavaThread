package com.wuxiongwei.java.thread.akka.demo50.stm2;

import akka.actor.UntypedActor;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

import java.lang.ref.Reference;

/**
 * <p>
 * Copyright: Copyright (c) 2020/11/19  2:53 下午
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@successchannel.com
 * @version 1.0.0
 */
public class CompanyActor extends UntypedActor {
    private Ref.View<Integer> cou = STM.newRef(100);//定义账号余额
    @Override
    public void onReceive(Object message) throws Exception, Exception {
//        if(message instanceof Corr)
    }
}

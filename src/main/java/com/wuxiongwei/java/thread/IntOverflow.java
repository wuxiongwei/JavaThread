package com.wuxiongwei.java.thread;

/**
 * 这种错误不会有提示，如果在一个庞大的系统中出现这样的问题就很头疼了。
 */
public class IntOverflow {
    public static void main(String[] args) {
        Integer a =  1222222222;
        int b =  1222222222;
        System.out.println(a);
        System.out.println(b);
        int ave = (a+b)/2;
        System.out.println(ave);

    }
}

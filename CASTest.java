package com.example.cms.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    public volatile static AtomicInteger value = new AtomicInteger(1);

    public static int getValue(){
        return  value.get();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for(int i = 0;i<30;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //本次操作读取的value内存值的快照
                    int x = value.get();
                    System.out.println(Thread.currentThread().getName()+"拿到value值:"+x);
                    //如果在此期间内存中value值与x快照值相等，则表明没有其他线程去修改value值
                    //则本次操作成功，修改value值为x+1，返回true
                    //如果多个线程同时去修改value值，则只有一个能成功，其余返回false，表示操作失败，本次操作无效
                    if (value.compareAndSet(x,x+1)){
                        System.out.println(Thread.currentThread().getName()+"成功进行操作：x的值为 "+value.get());
                    }else {
                        System.out.println(Thread.currentThread().getName()+"操作失败");
                    }
                }
            });
        }
    }

}

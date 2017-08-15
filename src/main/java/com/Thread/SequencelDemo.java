package com.Thread;

/**
 * ThreadLocal 理解为存放线程的局部变量的容器
 * Created by wm on 2017/8/15.
 */
public class SequencelDemo {

    private static ThreadLocal<Integer> numberContatiner = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {

            return 0;
        }
    };

    public int getNumber() {
        numberContatiner.set(numberContatiner.get() + 1);
        return numberContatiner.get();

    }

    public static void main(String[] args) {
        SequencelDemo sequencelDemo=new SequencelDemo();
        CurrentThread currentThread1=new CurrentThread(sequencelDemo);
        CurrentThread currentThread2=new CurrentThread(sequencelDemo);
        CurrentThread currentThread3=new CurrentThread(sequencelDemo);
        currentThread1.start();
        currentThread2.start();
        currentThread3.start();
    }

}

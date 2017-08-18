package com.threadDemo;

/**
 * Created by wm on 2017/8/15.
 */
public class CurrentThread extends Thread {

    private SequencelDemo sequencelDemo;

    public CurrentThread(SequencelDemo sequencelDemo) {

        this.sequencelDemo = sequencelDemo;
    }

    @Override
    public void run() {


        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "=>" + sequencelDemo.getNumber());
        }
    }
}

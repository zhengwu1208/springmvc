package com.song.ssm.study.thread;

/**
 * Created by Administrator on 2017/5/3.
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println("=================");
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        number = 10;
//        Thread.sleep(10);
//        Thread.yield();
        ready = true;
    }
}

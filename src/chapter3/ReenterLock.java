package chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ÖØÈëËø
 * Created by 13 on 2017/5/5.
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        lock.lock();
        for (int j = 0; j < 100; j++) {

            try {
                i++;
                System.out.println(Thread.currentThread().getName() + " " + i);
            } finally {

            }

        }
        lock.unlock();
    }

    public static void main(String args[]) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread thread1 = new Thread(reenterLock, "t1");
        Thread thread2 = new Thread(reenterLock, "t2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }

}

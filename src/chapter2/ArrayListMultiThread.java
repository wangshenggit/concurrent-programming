package chapter2;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by 13 on 2017/5/4.
 */
public class ArrayListMultiThread {
    static Vector<Integer> arrayList = new Vector<Integer>(10);

    public static class AddThread implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("Tread name:" + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                arrayList.add(i);
                System.out.println("Tread name:" + Thread.currentThread().getName() + " " + i);
            }
        }
    }

    /**
     * ArrayList是一个线程不安全的容器,多线程操作时会出现冲突,报错信息如下:
     * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 22
     * at java.util.ArrayList.add(ArrayList.java:441)
     * at chapter2.ArrayListMultiThread$AddThread.run(ArrayListMultiThread.java:27)
     * at java.lang.Thread.run(Thread.java:745)
     * <p>
     * Vector是一个线程安全的容器,可以代替ArrayList
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        Thread thread1 = new Thread(new AddThread(), "1");
        //Thread thread2 = new Thread(new AddThread(), "2");
        //System.out.println("Tread name:" + Thread.currentThread().getName());
        thread1.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            arrayList.add(i);
            System.out.println("Tread name:" + Thread.currentThread().getName() + "asdada= " + i);
        }


        thread1.join();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            arrayList.add(i);
            System.out.println("Tread name:" + Thread.currentThread().getName() + " cccccc" + i);
        }
        //thread2.start();
        //System.out.println("Tread name:111" + Thread.currentThread().getName());

        //System.out.println("Tread name:222" + Thread.currentThread().getName());
        //thread2.join();
        //System.out.println("Tread name:" + Thread.currentThread().getName());
        //System.out.println(arrayList.size());
        System.out.println("end");
    }

}

package chapter3;

import java.util.concurrent.CountDownLatch;

/**
 * @version v1.0
 * @ClassName CountDownLatchDemo01
 * @Description TODO
 * @Author wangheng
 * @Date 2019/10/24 0024 上午 10:42
 */
public class CountDownLatchDemo01 {

    public static void main(String[] args) {

        final CountDownLatch coutDownLatch = new CountDownLatch(1);
        System.out.println("主线程开始");

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        coutDownLatch.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("执行动作，线程" + Thread.currentThread().getName());


                }
            }).start();
        }

        try{
            System.out.println("开始睡眠");
            Thread.sleep(10000);
        }catch (Exception e){

        }

        coutDownLatch.countDown();
        System.out.println("主线程结束");


    }


}

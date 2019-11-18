package chapter3;

import java.util.concurrent.CountDownLatch;

/**
 * @version v1.0
 * @ClassName Test111
 * @Description TODO
 * @Author wangheng
 * @Date 2019/9/19 0019 ���� 16:12
 */
public class Test111 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);

        // ���δ������������ڵȴ�״̬��5��MyRunnable�߳�
        for (int i = 0; i < 5; ++i) {
            new Thread(new MyRunnable(countDown, await)).start();
        }

        System.out.println("���ڴ������ڵȴ�״̬���߳̿�ʼ����......");
        System.out.println("���ڴ������ڵȴ�״̬���̹߳�����ɣ��ȴ�״̬�߳̿�ʼ����......");
        countDown.countDown();
        await.await();
        System.out.println("Bingo!");
    }


}

class MyRunnable implements Runnable {

    private final CountDownLatch countDown;
    private final CountDownLatch await;

    public MyRunnable(CountDownLatch countDown, CountDownLatch await) {
        this.countDown = countDown;
        this.await = await;
    }

    public void run() {
        try {
            countDown.await();//�ȴ����߳�ִ����ϣ���ÿ�ʼִ���ź�...
            System.out.println("���ڵȴ����߳̿�ʼ�Լ�Ԥ�ڹ���......");
            await.countDown();//���Ԥ�ڹ�������������ź�...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

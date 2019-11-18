package test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
public class SemaphoreDemo {
    private static final Semaphore semaphore=new Semaphore(3);
    private static final ThreadPoolExecutor threadPool=new ThreadPoolExecutor(5,10,60,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());

    private static class InformationThread extends Thread{
        private final String name;
        private final int age;
        public InformationThread(String name,int age)
        {
            this.name=name;
            this.age=age;
        }

        public void run()
        {
            try
            {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+":��Һã�����"+name+"�ҽ���"+age+"�굱ǰʱ��Ϊ��"+System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(name+"Ҫ׼���ͷ����֤�ˣ���ǰʱ��Ϊ��"+System.currentTimeMillis());
                System.out.println("��ǰ��ʹ�õ������Ϊ��"+semaphore.availablePermits());
                semaphore.release();

            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args)
    {
        String[] name= {"����","����","�Ž�","��ǿ","�Զ�","����","����"};
        int[] age= {26,27,33,45,19,23,41};
        for(int i=0;i<7;i++)
        {
            Thread t1=new InformationThread(name[i],age[i]);
            threadPool.execute(t1);
        }
    }

}


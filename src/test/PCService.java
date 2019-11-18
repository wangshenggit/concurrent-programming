package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version v1.0
 * @ClassName PCService
 * @Description TODO
 * @Author wangheng
 * @Date 2019/10/16 0016 上午 11:43
 */
public class PCService {

    private Lock lock = new ReentrantLock();
    private boolean flage = false;
    private Condition condition = lock.newCondition();
    private int num = 1;

    public void produce(){
        try{
            lock.lock();
            while(flage == true){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"----生产");
            num++;
            System.out.println("num: " + num);
            flage = true;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consume(){
        try{
            lock.lock();
            while(flage == false){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"----生产");
            num++;
            System.out.println("num: " + num);
            flage = false;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }




}

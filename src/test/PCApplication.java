package test;

/**
 * @version v1.0
 * @ClassName PCApplication
 * @Description TODO
 * @Author wangheng
 * @Date 2019/10/16 0016 ���� 13:43
 */
public class PCApplication {
    public static void main(String[] args) {
        PCService service = new PCService();
        Runnable produce = new MyThreadProduce(service);
        Runnable consume = new MyThreadConsume(service);
        new Thread(produce, "������  ").start();
        new Thread(consume, "������  ").start();
    }
}

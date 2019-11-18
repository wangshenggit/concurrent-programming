package test;

/**
 * @version v1.0
 * @ClassName MyThreadProduce
 * @Description TODO
 * @Author wangheng
 * @Date 2019/10/16 0016 ионГ 11:53
 */
public class MyThreadConsume implements Runnable{

    private PCService pCService;
    private boolean isRun = true;

    public MyThreadConsume(PCService pCService){
        this.pCService = pCService;
    }

    @Override
    public void run() {
        while(isRun){
            pCService.consume();
        }

    }
}

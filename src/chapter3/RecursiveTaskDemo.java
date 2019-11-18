package chapter3;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class RecursiveTaskDemo extends RecursiveTask<Integer> {

    /**
     *  每个"小任务"最多只打印70个数
     */
    private static final int MAX = 70;
    private int arr[];
    private int start;
    private int end;


    public RecursiveTaskDemo(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end-start的值小于MAX时候，开始打印
        if((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }else {
            System.err.println("=====任务分解======");
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveTaskDemo left = new RecursiveTaskDemo(arr, start, middle);
            RecursiveTaskDemo right = new RecursiveTaskDemo(arr, middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[1000];
        for(int i = 0;i<1000;i++){
            arr[i] = new Random().nextInt(50);
        }

//        RecursiveTaskDemo recursiveTaskDemo = new RecursiveTaskDemo(arr, 0, 1000);
//        Integer a = recursiveTaskDemo.compute();
//        System.out.println(a);


        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //RecursiveTaskDemo recursiveTaskDemo = new RecursiveTaskDemo(arr, 0, 1000);
        Integer integer  = forkJoinPool.invoke(new RecursiveTaskDemo(arr, 0, 1000));
        System.out.println(integer);
        forkJoinPool.shutdown();

    }












}

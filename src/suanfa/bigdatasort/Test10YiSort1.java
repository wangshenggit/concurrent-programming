package suanfa.bigdatasort;


/*
 *一个排序算法题:从1亿个数字中取出最大的100个
 *装逼宝典：位图公式 `[arr[i]]=1;  将传统数组转换为位图数组就完成了排序！！！
 *
 *什么是位图？答：构建公式：bitmap[arr[i]]=1; 其中arr是我们的传统数组，bitmap是位图数组。
 *位图长度多少？答：bitmap.length=arr[i].maxValue; 因为位图数组基于传统数组来构建，所以位图数组的长度等于传统数组的最大值+1。
 *例如：现在存在传统数组{1,2,3,5,8}
 * 位图下标：0 1 2 3 4 5 6 7 8
 * 对应的值：0 1 1 1 0 1 0 0 1
    位图数组：｛0，1，1，1，0，1，0，0，1｝
    再次考验 传统数组 {2,4,3,1,9999999} 对应位图数组
         0 1 1 1 1 0 0 0 0 0 0 0 0 .....1
     1个int=4Byte   1亿个数字int = 4亿B/1024/1024=370MB
 *
 * 为什么使用位图？答：直接从1亿数据里面找Top100效率很低，通过构建位图，然后倒序输出位图就可以快速找到Top100.或者TopN
 *
 * 需要耗费多少时间？答：构建位图+倒序输出   = 累计耗时
 *
 * 从下面的测试结果我们可以看到，最耗时的是位图的构建过程.
 * 1.找最大值：14ms
 * 2.构建位图:149ms
 * 3.倒序输出:84ms
 * 4.累计耗时:234ms
 *
 * 总结：由此可见，位图排序，可以快速完成1亿数据的排序，顺序都排好了，拿Top100更是轻而易举的事。
 *

*/

import java.util.Random;

public class Test10YiSort1 {


    /**
     * 获取一亿数据获取前100个最大值
     * 1. 假设数组为 array[N] (N = 1 亿)，首先利用quicksort的原理把array分成两个部分，左边部分比 array[N - 1]
     * (array中的最后一个值，即pivot) 大， 右边部分比pivot 小。然后，可以得到 array[array.length - 1] (即 pivot)
     * 在整个数组中的位置，假设是 k.
     * 2. 如果 k 比 99 大，我们在数组[0, k - 1]里找前 100 最大值。 （继续递归）
     * 3. 如果 k 比 99 小， 我们在数组[k + 1, ..., N ]里找前 100 - (k + 1) 最大值。（继续递归）
     * 4. 如果 k == 99, 那么数组的前 100 个值一定是最大的。（退出）
     */


    public static void main(String[] args) {
        // the size of the array
        int number = 100000000;//(2*100000000)
        // the top k values
        int k = 100;
        // the range of the values in the array
        int range = 1000000001;

        // input for minHeap based method
        int[] array = new int[number];

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            array[i] = random.nextInt(range);
        }

        Test10YiSort1 topHundred = new Test10YiSort1();

        // start time
        long t1 = System.currentTimeMillis();
        topHundred.tophundred(array, 0, array.length - 1, k);

        // end time
        long t2 = System.currentTimeMillis();

        System.out.println("The total execution time of quicksort based method is" + (t2 - t1) + " millisecond");

        // print out the top k largest values in the top array
        System.out.println("The top " + k + " largest values are:");
        for (int i = 0; i < k; i++) {
            System.out.println(array[i]);
        }
    }

    private void tophundred(int[] array, int start, int end, int k) {
        int switchPointer = start;
        // array最后一个值作为pivot
        int pivot = array[end];
        for (int i = start; i < end; i++) {
            if (array[i] >= pivot) {
                swap(array, switchPointer, i);
                switchPointer++;
            }
        }

        // 交换后 array左边的值比pivot大   右边的值比pivot小
        swap(array, end, switchPointer);

        if (switchPointer < k - 1) {
            tophundred(array, switchPointer + 1, end, k);
        } else if (switchPointer == k - 1) {
            return;
        } else {
            tophundred(array, 0, switchPointer - 1, k);
        }

    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}

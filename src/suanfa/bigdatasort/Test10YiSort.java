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

public class Test10YiSort {

    public static int[] bitmapSort(int[] inputArray) {

        // 1.获取传统数组的最大值.即位图数组的长度
        long findMaxBegin = System.currentTimeMillis();
        int maxValue = inputArray[0];
        for (int i = 0; i < inputArray.length; ++i) {
            if (maxValue < inputArray[i]) {
                maxValue = inputArray[i];
            }
        }
        System.out.println("1.找最大值:" + (System.currentTimeMillis() - findMaxBegin) + "ms");

        //2.将传统数组转换成位图数组
        long bitmapBegin = System.currentTimeMillis();
        byte[] bitmap = new byte[maxValue + 1];
        //bitmap[arr[i]]=1
        for (int i = 0; i < inputArray.length; ++i) {
            bitmap[inputArray[i]] = 1;    // 转换规则：传统数组的值就是位图数组的值需要置1的下标
        }
        System.out.println("2.构建位图:" + (System.currentTimeMillis() - bitmapBegin) + "ms");

        //3.倒序遍历位图 ,如果值为1则输出 . 相当于返回一个排序好的传统数组。
        long descBegin = System.currentTimeMillis();
        int[] result = new int[inputArray.length];
        int index = 0;
        for (int i = bitmap.length - 1; i >= 0 & index < result.length; i--) {
            if (bitmap[i] == 1) {
                result[index++] = i;
            }
        }
        System.out.println("3.倒序输出:" + (System.currentTimeMillis() - descBegin) + "ms");
        System.out.println("4.累计耗时:" + (System.currentTimeMillis() - findMaxBegin) + "ms");

        return result;

    }

    public static void main(String[] args) {
        // 设置传统数组的长度
        int inputArray[] = new int[10000000];
        // 产生随机数的范围0~maxNum
        for (int i = 0; i < inputArray.length; ++i) {
            inputArray[i] = (int) (Math.random() * inputArray.length);
        }
        //获取Top100
        int[] result = Test10YiSort.bitmapSort(inputArray);
        for (int i = 0; i < 100; i++) {
            System.out.print(result[i] + ",");
        }


    }
}
package suanfa.bigdatasort;


/*
 *һ�������㷨��:��1�ڸ�������ȡ������100��
 *װ�Ʊ��䣺λͼ��ʽ `[arr[i]]=1;  ����ͳ����ת��Ϊλͼ�������������򣡣���
 *
 *ʲô��λͼ���𣺹�����ʽ��bitmap[arr[i]]=1; ����arr�����ǵĴ�ͳ���飬bitmap��λͼ���顣
 *λͼ���ȶ��٣���bitmap.length=arr[i].maxValue; ��Ϊλͼ������ڴ�ͳ����������������λͼ����ĳ��ȵ��ڴ�ͳ��������ֵ+1��
 *���磺���ڴ��ڴ�ͳ����{1,2,3,5,8}
 * λͼ�±꣺0 1 2 3 4 5 6 7 8
 * ��Ӧ��ֵ��0 1 1 1 0 1 0 0 1
    λͼ���飺��0��1��1��1��0��1��0��0��1��
    �ٴο��� ��ͳ���� {2,4,3,1,9999999} ��Ӧλͼ����
         0 1 1 1 1 0 0 0 0 0 0 0 0 .....1
     1��int=4Byte   1�ڸ�����int = 4��B/1024/1024=370MB
 *
 * Ϊʲôʹ��λͼ����ֱ�Ӵ�1������������Top100Ч�ʺܵͣ�ͨ������λͼ��Ȼ�������λͼ�Ϳ��Կ����ҵ�Top100.����TopN
 *
 * ��Ҫ�ķѶ���ʱ�䣿�𣺹���λͼ+�������   = �ۼƺ�ʱ
 *
 * ������Ĳ��Խ�����ǿ��Կ��������ʱ����λͼ�Ĺ�������.
 * 1.�����ֵ��14ms
 * 2.����λͼ:149ms
 * 3.�������:84ms
 * 4.�ۼƺ�ʱ:234ms
 *
 * �ܽ᣺�ɴ˿ɼ���λͼ���򣬿��Կ������1�����ݵ�����˳���ź��ˣ���Top100��������׾ٵ��¡�
 *

*/

import java.util.Random;

public class Test10YiSort1 {


    /**
     * ��ȡһ�����ݻ�ȡǰ100�����ֵ
     * 1. ��������Ϊ array[N] (N = 1 ��)����������quicksort��ԭ���array�ֳ��������֣���߲��ֱ� array[N - 1]
     * (array�е����һ��ֵ����pivot) �� �ұ߲��ֱ�pivot С��Ȼ�󣬿��Եõ� array[array.length - 1] (�� pivot)
     * �����������е�λ�ã������� k.
     * 2. ��� k �� 99 ������������[0, k - 1]����ǰ 100 ���ֵ�� �������ݹ飩
     * 3. ��� k �� 99 С�� ����������[k + 1, ..., N ]����ǰ 100 - (k + 1) ���ֵ���������ݹ飩
     * 4. ��� k == 99, ��ô�����ǰ 100 ��ֵһ�������ġ����˳���
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
        // array���һ��ֵ��Ϊpivot
        int pivot = array[end];
        for (int i = start; i < end; i++) {
            if (array[i] >= pivot) {
                swap(array, switchPointer, i);
                switchPointer++;
            }
        }

        // ������ array��ߵ�ֵ��pivot��   �ұߵ�ֵ��pivotС
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

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

public class Test10YiSort {

    public static int[] bitmapSort(int[] inputArray) {

        // 1.��ȡ��ͳ��������ֵ.��λͼ����ĳ���
        long findMaxBegin = System.currentTimeMillis();
        int maxValue = inputArray[0];
        for (int i = 0; i < inputArray.length; ++i) {
            if (maxValue < inputArray[i]) {
                maxValue = inputArray[i];
            }
        }
        System.out.println("1.�����ֵ:" + (System.currentTimeMillis() - findMaxBegin) + "ms");

        //2.����ͳ����ת����λͼ����
        long bitmapBegin = System.currentTimeMillis();
        byte[] bitmap = new byte[maxValue + 1];
        //bitmap[arr[i]]=1
        for (int i = 0; i < inputArray.length; ++i) {
            bitmap[inputArray[i]] = 1;    // ת�����򣺴�ͳ�����ֵ����λͼ�����ֵ��Ҫ��1���±�
        }
        System.out.println("2.����λͼ:" + (System.currentTimeMillis() - bitmapBegin) + "ms");

        //3.�������λͼ ,���ֵΪ1����� . �൱�ڷ���һ������õĴ�ͳ���顣
        long descBegin = System.currentTimeMillis();
        int[] result = new int[inputArray.length];
        int index = 0;
        for (int i = bitmap.length - 1; i >= 0 & index < result.length; i--) {
            if (bitmap[i] == 1) {
                result[index++] = i;
            }
        }
        System.out.println("3.�������:" + (System.currentTimeMillis() - descBegin) + "ms");
        System.out.println("4.�ۼƺ�ʱ:" + (System.currentTimeMillis() - findMaxBegin) + "ms");

        return result;

    }

    public static void main(String[] args) {
        // ���ô�ͳ����ĳ���
        int inputArray[] = new int[10000000];
        // ����������ķ�Χ0~maxNum
        for (int i = 0; i < inputArray.length; ++i) {
            inputArray[i] = (int) (Math.random() * inputArray.length);
        }
        //��ȡTop100
        int[] result = Test10YiSort.bitmapSort(inputArray);
        for (int i = 0; i < 100; i++) {
            System.out.print(result[i] + ",");
        }


    }
}
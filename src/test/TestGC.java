package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName TestGC
 * @Description TODO
 * @Author wangheng
 * @Date 2019/9/27 0027 ���� 16:40
 */
public class TestGC {
    //�����������
    private static final Map map = new HashMap();
    public static void main(String args[]){
        try {
            Thread.sleep(10000);//����visualvmʱ��
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ѭ����Ӷ��󵽻���
        for(int i=0; i<1000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("first");
        //Ϊdump�����ṩʱ��
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<1000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("second");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<3000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("third");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<4000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("forth");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("qqqq");
    }
}

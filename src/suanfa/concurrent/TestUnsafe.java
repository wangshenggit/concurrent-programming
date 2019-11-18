package suanfa.concurrent;

import java.lang.reflect.Field;
import sun.misc.Unsafe;


public class TestUnsafe {

    private static Unsafe unsafe;

    static {
        try {
            //ͨ�������ȡrt.jar�µ�Unsafe��
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println("Get Unsafe instance occur error"+ e);
        }
    }




    public static void main(String[] args) throws Exception
    {
        Class clazz = Target.class;
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("fieldName:fieldOffset");
        for (Field f : fields) {
            // ��ȡ����ƫ����������ͨ�����ƫ��������������
            System.out.println(f.getName() + ":" + unsafe.objectFieldOffset(f));
        }
        Target target = new Target();
        Field intFiled  =  clazz.getDeclaredField("intParam")  ;
        int a=(Integer)intFiled.get(target) ;
        System.out.println("ԭʼֵ��:"+a);
        //intParam���ֶ�ƫ����12 ԭʼֵ��3 ����Ҫ��Ϊ10
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));
        int b=(Integer)intFiled.get(target) ;
        System.out.println("�ı�֮���ֵ��:"+b);

        for (Field f : fields) {
            // ��ȡ����ƫ����������ͨ�����ƫ��������������
            System.out.println(f.getName() + ":" + unsafe.objectFieldOffset(f));
        }

        //���ʱ���Ѿ���Ϊ10��,���Ի᷵��false
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));

        System.out.println(unsafe.compareAndSwapObject(target, 24, null, "5"));
    }
}


class Target {
    int intParam=3;
    long longParam;
    String strParam;
    String strParam2;
}
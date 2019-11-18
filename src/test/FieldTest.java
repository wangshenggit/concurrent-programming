package test;

import java.lang.reflect.Field;

public class FieldTest {

    private char charField = 'A';
    private byte byteField = 110;
    private short shortField = 111;
    private int intField = 112;
    private long longField = 113;
    private float floatField = 1.23F;
    private double doubleField = 1.23;
    private boolean booleanField = true;
    private Object objField = "哈哈哈";

    public static void main(String[] args) throws Exception {
        Class<FieldTest> fieldTestClass = FieldTest.class;
        /**
         * 获取char类型的静态或实例字段的值，或通过扩展转换将另一个基本数据类型转换为char类型的值
         */
        Field charField = fieldTestClass.getDeclaredField("charField");
        FieldTest charFieldTest = new FieldTest();
        System.out.println(charField.getChar(charFieldTest)); // A
        charField.setChar(charFieldTest, 'B');
        System.out.println(charField.getChar(charFieldTest)); // B


    }
}


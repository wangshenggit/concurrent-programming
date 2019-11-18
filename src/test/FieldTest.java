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
    private Object objField = "������";

    public static void main(String[] args) throws Exception {
        Class<FieldTest> fieldTestClass = FieldTest.class;
        /**
         * ��ȡchar���͵ľ�̬��ʵ���ֶε�ֵ����ͨ����չת������һ��������������ת��Ϊchar���͵�ֵ
         */
        Field charField = fieldTestClass.getDeclaredField("charField");
        FieldTest charFieldTest = new FieldTest();
        System.out.println(charField.getChar(charFieldTest)); // A
        charField.setChar(charFieldTest, 'B');
        System.out.println(charField.getChar(charFieldTest)); // B


    }
}


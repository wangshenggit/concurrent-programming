package test;

import java.lang.reflect.Field;

/**
 * @version v1.0
 * @ClassName Student
 * @Description TODO
 * @Author wangheng
 * @Date 2019/9/20 0020 ÏÂÎç 16:59
 */
public class Student extends Person{

//    public void test() throws Exception{
//        show();
//        Field f = this.getClass().getSuperclass().getDeclaredField("age");
//        f.setAccessible(true);
//        f.get(new Student());
//        show();
//
//
//
//
//    }

    public static void main(String[] args) throws Exception{
        Student student = new Student();
        student.show();
        Field field = student.getClass().getSuperclass().getField("age");
        field.setAccessible(true);
        field.setInt(student.getClass(), 10);
        student.show();






    }



}

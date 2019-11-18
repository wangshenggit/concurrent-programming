package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @version v1.0
 * @ClassName UnsafeTest
 * @Description TODO
 * @Author wangheng
 * @Date 2019/11/11 0011 …œŒÁ 10:59
 */
public class UnsafeTest {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

//        User user1 = new User();
//        // ¥Ú”°10
//        System.out.println(user1.age);
//
//        User user2 = (User) unsafe.allocateInstance(User.class);
//         // ¥Ú”°0
//        System.out.println(user2.age);

        User user = new User();
        Field age = user.getClass().getDeclaredField("age");

        unsafe.putInt(user, unsafe.objectFieldOffset(age), 20);

        System.out.println(user.getAge());





    }
}

class User {
    int age;

    public User() {
        this.age = 10;
    }
    public int getAge() {
        return age;
    }
}

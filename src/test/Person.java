package test;

/**
 * @version v1.0
 * @ClassName Person
 * @Description TODO
 * @Author wangheng
 * @Date 2019/9/20 0020 обнГ 16:58
 */
public class Person {
    private String username;
    private String password;
    private Integer age;

    public Person() {
        username = "1231232";
        password = "adada";
        age = 21;
    }

    public void show() {
        System.out.println("username=" + username + "\npassword=" + password + "\nage = " + age);
    }
}

package proxy;

/**
 * @version v1.0
 * @ClassName MyPerson
 * @Description TODO
 * @Author wangheng
 * @Date 2019/10/17 0017 ÏÂÎç 14:18
 */
public class MyPerson implements Person {
    private String name;

    public MyPerson(String name) {
        this.name = name;
    }

    @Override
    public void doEat() {
        System.out.println(this.name + "³Ô·¹");
    }

    @Override
    public void doSleep() {
        System.out.println(this.name + "Ë¯¾õ");
    }
}

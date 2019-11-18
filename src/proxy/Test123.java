package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version v1.0
 * @ClassName Test123
 * @Description TODO
 * @Author wangheng
 * @Date 2019/10/17 0017 下午 14:20
 */
public class Test123 {
    public static void main(String[] args) {
        MyPerson myPerson = new MyPerson("zhangsan");
        Person myPersonProxy = (Person)Proxy.newProxyInstance(myPerson.getClass().getClassLoader(), new Class[]{Person.class}, new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("做事之前要洗手");
                Object object = method.invoke(myPerson, args);
                return object;
            }
        });
        myPersonProxy.doEat();
    }


}

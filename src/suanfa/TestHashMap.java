package suanfa;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version v1.0
 * @ClassName TestHashMap
 * @Description TODO
 * @Author wangheng
 * @Date 2019/11/13 0013 ионГ 11:23
 */
public class TestHashMap {

    public static void main(String[] args) {


        Map map = new ConcurrentHashMap<String, Object>();

        map.put("wangsheng", "test12344");
        map.put("wangsheng1", "test12344asdda");
        map.put("lisi", "test12344asdda");
//
//
//        System.out.println();
//        String key = "wangsheng1";
//        int n = 16;
//
//        int hash = spread(key.hashCode());
//        int i = (n - 1) & hash;
//
//        System.out.println(i);

        //System.out.println(15 & 16);





    }
    static final int HASH_BITS = 0x7fffffff;
    public static int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }


}

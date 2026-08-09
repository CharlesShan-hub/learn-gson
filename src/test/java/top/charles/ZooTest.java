package top.charles;

import org.junit.Test;

import java.util.Arrays;

public class ZooTest extends BaseTest {
    @Test
    public void test() {
        // 对象 → JSON
        Zoo z1 = new Zoo(Arrays.asList(
                new Animal("Tom", 1940),
                new Animal("Jerry", 1940))
        );
        String json = prettyGson.toJson(z1);
        System.out.println(json);
        // {
        //   "animals": [
        //     {
        //       "name": "Tom",
        //       "birthYear": 1940
        //     },
        //     {
        //       "name": "Jerry",
        //       "birthYear": 1940
        //     }
        //   ]
        // }

        // JSON → 对象
        Zoo z2 = gson.fromJson(json, Zoo.class);
        System.out.println(z2);
        // Zoo(animals=[Animal(name=Tom, birthYear=1940), Animal(name=Jerry, birthYear=1940)])
    }

    @Test
    public void testBigNumber() {
        long donation = 90071992547409933L;  // 超过 2^53

        // ✅ 序列化：精确
        String json = gson.toJson(donation);
        System.out.println(json);
        // 90071992547409933

        // ✅ 反序列化到 Long：精确
        Long l = gson.fromJson(json, Long.class);
        System.out.println(l);
        // 90071992547409933

        // ❌ 反序列化到 Double：丢精度
        Double d = gson.fromJson(json, Double.class);
        System.out.println(d.longValue());
        // 90071992547409936  ← 值变了

        // ❌ 反序列化到 Object：默认变成 Double，同样丢
        Object obj = gson.fromJson(json, Object.class);
        System.out.println(obj.getClass());  // class java.lang.Double
    }
}
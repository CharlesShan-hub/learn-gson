package top.charles;

import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalTest extends BaseTest {
    @Test
    public void test() {
        // 对象 → JSON
        Animal a1 = new Animal("Tom", 1938);
        String json = gson.toJson(a1);
        System.out.println(json);
        // {"name":"Tom","birthYear":1938}

        // JSON → 对象
        Animal a2 = gson.fromJson(json, Animal.class);
        System.out.println(a2);
        // Animal(name=Tom, birthYear=1938)
    }

    @Test
    public void testPrettyPrint() {
        Animal a1 = new Animal("Tom", 1938);
        Animal a2 = new Animal("Jerry", 1940);
        List<Animal> list = Arrays.asList(a1, a2);

        // 默认：紧凑输出
        String compactJson = gson.toJson(list);
        System.out.println(compactJson);
        // [{"name":"Tom","birthYear":1938},{"name":"Jerry","birthYear":1940}]

        // 美化：格式化输出
        String prettyJson = prettyGson.toJson(list);
        System.out.println(prettyJson);
        // [
        //   {
        //     "name": "Tom",
        //     "birthYear": 1938
        //   },
        //   {
        //     "name": "Jerry",
        //     "birthYear": 1940
        //   }
        // ]
    }

    @Test
    public void testList(){
        // List -> JSON
        List<Animal> list = Arrays.asList(
                new Animal("Tom",1938),
                new Animal("Jerry", 1940)
        );
        String json = prettyGson.toJson(list);
        System.out.println(json);
        // [
        //   {
        //     "name": "Tom",
        //     "birthYear": 1938
        //   },
        //   {
        //     "name": "Jerry",
        //     "birthYear": 1940
        //   }
        // ]

        // JSON -> List
        List<Animal> list2 = gson.fromJson(json,
                new TypeToken<List<Animal>>(){}.getType()
        );
        System.out.println(list2);
        // [Animal(name=Tom, birthYear=1938), Animal(name=Jerry, birthYear=1940)]
    }

    @Test
    public void testMap(){
        // Map -> JSON
        Map<String, Animal> zoo = new HashMap<>();
        zoo.put("cat", new Animal("Tom", 1938));
        zoo.put("mouse", new Animal("Jerry", 1940));
        String json = prettyGson.toJson(zoo);
        System.out.println(json);
        // {
        //   "mouse": {
        //     "name": "Jerry",
        //     "birthYear": 1940
        //   },
        //   "cat": {
        //     "name": "Tom",
        //     "birthYear": 1938
        //   }
        // }

        // JSON -> Map
        Map<String, Animal> zoo2 = gson.fromJson(json,
                new TypeToken<Map<String, Animal>>(){}.getType()
        );
        System.out.println(zoo2);
        // {mouse=Animal(name=Jerry, birthYear=1940), cat=Animal(name=Tom, birthYear=1938)}
    }

    @Test
    public void testListError() {
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"Tom\",\n" +
                "    \"birthYear\": 1938\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Jerry\",\n" +
                "    \"birthYear\": 1940\n" +
                "  }\n" +
                "]";

        // ❌ 错误写法：直接传 List.class
        // List<Animal> list = gson.fromJson(json, List.class);
        List list = gson.fromJson(json, List.class);

        System.out.println(list.getClass());
        // class java.util.ArrayList

        System.out.println(list.get(0).getClass());
        // class com.google.gson.internal.LinkedTreeMap
        // ↑ 每个元素都是 LinkedTreeMap，不是 Animal！

        // 如果尝试强转或当 Animal 用：
        // Animal a1 = list.get(0);  // ❌ ClassCastException
        // a1.getName();             // ❌ 编译不报错，运行时报
    }

    @Test
    public void testNull(){
        Animal a1 = new Animal(null, 1940);

        // 默认：null 字段不输出
        String json1 = gson.toJson(a1);
        System.out.println(json1);
        // {"birthYear":1940}
        // ↑ name 字段消失了

        // 开启 serializeNulls：null 字段保留
        String json2 = gsonWithNull.toJson(a1);
        System.out.println(json2);
        // {
        //   "name": null,
        //   "birthYear": 1940
        // }
        // ↑ null 字段保留了

        // 反序列化：无论哪种 JSON，都能正确填充 null
        Animal a2 = gson.fromJson(json1, Animal.class);
        System.out.println(a2); // Animal(name=null, birthYear=1940)

        Animal a3 = gson.fromJson(json2, Animal.class);
        System.out.println(a3); // Animal(name=null, birthYear=1940)
    }
}

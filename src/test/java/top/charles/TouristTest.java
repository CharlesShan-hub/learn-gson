package top.charles;

import org.junit.Test;
import java.util.Date;

public class TouristTest extends BaseTest{
    @Test
    public void testDefault() {
        Tourist user = new Tourist("Dimo", new Date());
        String json = prettyGson.toJson(user);

        System.out.println(json);
        // {
        //   "name": "Dimo",
        //   "birthday": "Aug 6, 2026, 2:03:41 PM"
        // }
        // 默认格式，不是我们常用的
    }

    @Test
    public void testCustomFormat() {
        Tourist user = new Tourist("Guardian Dog", new Date());
        String json = prettyDataGson.toJson(user);

        System.out.println(json);
        // {
        //   "name": "Guardian Dog",
        //   "birthday": "2026-08-06 14:03:41"
        // }
    }

    @Test
    public void testDeserialize() {
        String json = "{\"name\":\"Elsa\",\"age\":30,\"birthday\":\"2025-03-21 14:30:00\"}";

        Tourist user = prettyDataGson.fromJson(json, Tourist.class);
        System.out.println(user);
        // Tourist(name=Elsa, birthday=Fri Mar 21 14:30:00 CST 2025)
    }
}
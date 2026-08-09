package top.charles;

import org.junit.Test;

public class BossTest extends BaseTest{

    @Test
    public void testBoss() {
        // 序列化：cost 不输出，profit 输出
        Boss boss = new Boss(100000.0);
        String json = exposeGson.toJson(boss);
        System.out.println(json);
        // {"total":100000.0,"profit":0.0} 这是老板还不知道自己赚了多少

        // 会计传来花费
        String fromAccountant = "{\"total\":100000.0,\"cost\":80000.0,\"profit\":99999.0}";

        Boss boss2 = exposeGson.fromJson(fromAccountant, Boss.class);
        System.out.println(boss2);
        // Boss(total=100000.0, cost=80000.0, profit=20000.0)
    }
}
package top.charles;

import org.junit.Test;

import java.util.Arrays;

public class ManagerTest extends BaseTest{
    @Test
    public void testManager() {
        // 创建经理：自己工资 10000，手下 张三 5000、李四 6000
        Manager manager = new Manager("张经理", 10000.0, Arrays.asList(
                new Employee("张三", 5000.0),
                new Employee("李四", 6000.0)
        ));
        System.out.println("构造时 totalExpense 自动计算: "+manager);
        // Manager(employees=[Employee(name=张三, salary=5000.0), Employee(name=李四, salary=6000.0)], totalExpense=21000.0)
        // 可以看到已经有了totalExpense

        // 序列化：totalExpense 不输出（transient）
        String json = prettyGson.toJson(manager);
        System.out.println("序列化: " + json);
        // {
        //   "employees": [
        //     {
        //       "ename": "张三",
        //       "sal": 5000.0
        //     },
        //     {
        //       "ename": "李四",
        //       "sal": 6000.0
        //     }
        //   ],
        //   "ename": "张经理",
        //   "sal": 10000.0
        // }
        // ↑ totalExpense 不输出，因为它是 transient

        // 反序列化：totalExpense 恢复默认 0.0
        Manager manager2 = gson.fromJson(json, Manager.class);
        System.out.println("反序列化后 totalExpense: " + manager2);
        // Manager(employees=[Employee(name=张三, salary=5000.0), Employee(name=李四, salary=6000.0)], totalExpense=21000.0)
        // ↑ 走的是 getter，重新算了
    }
}
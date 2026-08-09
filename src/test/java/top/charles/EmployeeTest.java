package top.charles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeTest extends BaseTest{
    @Test
    public void testSerialize() {
        Employee employee = new Employee("Jack", 10000.5);
        String json = prettyGson.toJson(employee);

        System.out.println(json);
        // {
        //   "ename": "Jack",
        //   "sal": 10000.5
        // }
        // ↑ name 变成 ename，salary 变成 sal
    }

    @Test
    public void testDeserializeWithAlternate() {
        String json = "{\"employee_name\":\"Tom\",\"sal\":8000.5}";

        Employee employee = gson.fromJson(json, Employee.class);
        System.out.println(employee);
        // Employee(name=Tom, salary=8000.5)
    }

    @Test
    public void testLoop(){
        // 张经理：动物管理线负责人，兼任培训讲师
        Manager zhang = new Manager("张经理", 10000.0, new ArrayList<>(Arrays.asList(
                new Employee("张三", 5000.0),
                new Employee("李四", 6000.0)
        )));

        // 小李经理：培训线负责人，兼任动物管理顾问
        Manager li = new Manager("小李经理", 8000.0, new ArrayList<>(Arrays.asList(
                new Employee("王五", 4000.0)
        )));

        // 条块划分：互相挂职，往对方的员工列表里加
        zhang.getEmployees().add(li);   // 张经理手下有小李（培训线）
        li.getEmployees().add(zhang);   // 小李手下有张经理（动物管理线）

        // ❌ 直接序列化
        // String json = gson.toJson(zhang);
        // StackOverflowError：张经理 -> 小李 -> 张经理 -> 小李 ... 无限递归
    }
}
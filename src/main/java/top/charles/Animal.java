package top.charles;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

// @Data // getter/setter 可省略，Gson 通过反射直接访问字段
@AllArgsConstructor
@NoArgsConstructor // 必须有默认构造器（无参构造）
@ToString
public class Animal {
    private String name;
    private int birthYear;
}
